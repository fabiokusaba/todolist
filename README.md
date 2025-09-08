# 📝 Projeto de Estudos - Curso Introdutório Java com Spring Boot

Este repositório contém o projeto desenvolvido durante o Curso Introdutório de Java com Spring Boot da Rocketseat
, ministrado pela professora Daniele Leão.

O objetivo do projeto é praticar conceitos fundamentais de Java e Spring Boot, aplicando-os em um sistema simples de gerenciamento de usuários e tarefas.

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database
- Basic Auth

## ⚙️ Funcionalidades

### 👤 Usuários

- Criar usuário

### ✅ Tarefas

- Criar tarefa para um usuário
- Listar tarefas de um usuário
- Atualizar tarefa de um usuário

## 🔑 Autenticação

A aplicação utiliza Basic Auth para proteger os endpoints de tarefas.

Cada requisição deve ser autenticada com usuário e senha válidos.

## 📌 Endpoints principais

- Usuários

    POST /users → Criar novo usuário

- Tarefas

    POST /tasks → Criar tarefa para um usuário

    GET /tasks → Listar tarefas de um usuário

    PUT /tasks/{id} → Atualizar tarefa de um usuário

## 🎯 Objetivo

Este projeto é apenas para estudos, servindo como base para aprender conceitos fundamentais de API REST com Spring Boot e boas práticas de desenvolvimento backend em Java.