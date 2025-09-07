package br.com.fabiokusaba.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.fabiokusaba.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    private final IUserRepository userRepository;

    public FilterTaskAuth(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Verificando a rota
        var servletPath = request.getServletPath();

        if (!servletPath.startsWith("/tasks/")) {
            filterChain.doFilter(request, response);
        } else {
            // Pegar a autenticação (usuário e senha)
            var authorization = request.getHeader("Authorization");

            var authEncoded = authorization.substring("Basic".length()).trim();

            var authDecoded = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecoded);

            var credentials = authString.split(":");
            var username = credentials[0];
            var password = credentials[1];

            // Validar usuário
            var user = this.userRepository.findByUsername(username);

            if (user == null) {
                response.sendError(401, "Usuário não autorizado!");
            }

            // Validar senha
            var result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

            if (!result.verified) {
                response.sendError(401, "Usuário ou senha inválidos!");
            }

            // Avança para próxima etapa
            request.setAttribute("userId", user.getId());
            filterChain.doFilter(request, response);
        }
    }

}
