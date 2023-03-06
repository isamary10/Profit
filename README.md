# Profit

Uma API para curso de investimentos para iniciantes e simulação de investimentos

---

## Endepoints

- [Cursos](#cursos)
  - [cadastrar](#cadastrar-curso)
  - [detalhar](#detalhar-curso)
  - [editar](#editar-curso)
  - [delelar](#deletar-curso)
  - [listar](#listar-cursos)

- [Simulação de investimento](#simulacao)
  - [simular](#simular-investimento)

- [Usuarios](#usuarios)
  - [cadastrar](#cadastrar-usuario)
  - [detalhar](#detalhar-usuario)
  - [editar](#editar-usuario)
  - [deletar](#deletar-usuario)
  - [listar](#listar-usuarios)
---

## Cursos

### Cadastrar Curso

`POST` /profit/api/user/{userId}/curso

**Campos de Requisição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| id_usuario | int | sim | id do usuario previamente cadastrado
| nome | texto | sim | o nome do curso
| duracao | LocalTime | sim | a duração do curso
| descricao | texto | sim | descrição do curso
| link | texto | sim | link do curso

**Exemplo de corpo de requisição**

```js
{
  id_usuario: 1,
  nome: 'Como começar a investir',
  duracao: '3h',
  descricao: 'Aprenda os principais conceitos para iniciar no mundos dos investimentos.',
  link: 'https://edu.b3.com.br/play/curso/52610594'
}
```

**Códigos de Repostas**

| codigo | descricao
|-|-
| 201 | curso cadastrado com sucesso
| 400 | campos inválidos
| 408 | tempo esgotado

---

### Detalhar curso

`GET` /profit/api/curso/{id}

**Exemplo de corpo de resposta**

```js
{
  usuario{
    id_usuario: 1
  }
  nome: 'Como começar a investir',
  duracao: '3h',
  descricao: 'Aprenda os principais conceitos para iniciar no mundos dos investimentos.',
  link: 'https://edu.b3.com.br/play/curso/52610594'
}
```
**Códigos de Repostas**

| codigo | descricao
|-|-
| 200 | dados do curso retornados
| 404 | não existe curso com o id informado
| 500 | internal server error

---

### Editar curso

`PUT` /profit/api/user/{userId}/curso/{cursoId}

**Campos de Edição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| id_usuario | int | sim | id do usuario previamente cadastrado
| nome | texto | sim | o nome do curso
| duracao | LocalTime | sim | a duração do curso
| descricao | texto | sim | descrição do curso
| link | texto | sim | link do curso

**Exemplo de corpo de requisição**

```js
{
  id_usuario: 1,
  nome: 'Como começar a investir',
  duracao: '3h',
  descricao: 'Aprenda os principais conceitos para iniciar no mundos dos investimentos.',
  link: 'https://edu.b3.com.br/play/curso/52610594'
}
```

**Códigos de Repostas**

| codigo | descricao
|-|-
| 200 | curso alterado com sucesso
| 400 | campos inválidos
| 404 | não existe curso com o id informado
| 408 | tempo esgotado
| 500 | internal server error

---
### Deletar curso

`DELETE` /profit/api/user/{userId}/curso/{cursoId}

**Campos**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| id_usuario | int | sim | id do usuario previamente cadastrado
| nome | texto | sim | o nome do curso
| duracao | LocalTime | sim | a duração do curso
| descricao | texto | sim | descrição do curso
| link | texto | sim | link do curso

**Exemplo de corpo de requisição**

```js
{
  usuario{
    id_usuario: 1
  }
  nome: 'Como começar a investir',
  duracao: '3h',
  descricao: 'Aprenda os principais conceitos para iniciar no mundos dos investimentos.',
  link: 'https://edu.b3.com.br/play/curso/52610594'
}
```

**Códigos de Repostas**

| codigo | descricao
|-|-
| 200 | curso removido com sucesso
| 404 | não existe curso com o id informado
| 408 | tempo esgotado
| 500 | internal server error

---
### Listar Cursos

`GET` /profit/api/cursos

**Exemplo de corpo de requisição**

```js
{
  nome: 'Como começar a investir',
  duracao: '3h',
  descricao: 'Aprenda os principais conceitos para iniciar no mundos dos investimentos.',
  link: 'https://edu.b3.com.br/play/curso/52610594'
}
```
```js
{
  nome: 'Entenda o que é renda variável',
  duracao: '1h',
  descricao: 'Entenda de vez a diferença entre renda fixa e renda variável e conheça as características e benefícios da renda variável.',
  link: 'https://edu.b3.com.br/play/curso/52610594'
}
```
**Códigos de Repostas**

| codigo | descricao
|-|-
| 200 | dados do cursos retornados
| 404 | cursos não encontrados
| 500 | internal server error

<br>

---

## Simulacao
### Simular investimento

`POST` /profit/api/simulador

**Campos de Requisição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| valor_inicial | decimal| sim | o valor inicial do investimento
| valor_aporte | decimal | nao | a valor do aporte mensal
| tipo_investimento | texto | sim | o tipo do investimento
| nivel| texto | sim | nivel de risco do investimento
| tempo_invest | texto | sim | tempo que irá manter o dinheiro investido

**Exemplo de corpo de requisição**

```js
{
  valor_inicial: 10.000,
  valor_aporte: 500.00,
  tipo_investimento: 'CDB 16% a.a.'
  nivel: 'Intermediario',
  tempo_invest: 'De 1 a 5 anos'
}
```

**Códigos de Repostas**

| codigo | descricao
|-|-
| 201 | simulação retornada com sucesso
| 400 | campos inválidos

<br>

---

## Usuarios

### Cadastrar usuario

`POST` /profit/api/usuario

**Campos de Requisição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| nome | texto | sim | o nome completo do usuário
| cpf | texto | sim | o cep do usuario deve conter 11 digitos
| email | email | sim | o email do usuário
| telefone | texto | sim | o telefone do usuario

**Exemplo de corpo de requisição**

```js
{
  nome: 'Isabelle Souza Santos',
  cpf: '345.187.952-24',
  email: 'isabelle.souza@gmail.com',
  telefone: '(11)92456-2525'
}
```

**Códigos de Repostas**

| codigo | descricao
|-|-
| 201 | usuario cadastrado com sucesso
| 400 | campos inválidos
| 408 | tempo esgotado

---

### Detalhar usuario

`GET` /profit/api/usuario/{id}

**Exemplo de corpo de resposta**

```js
{
  nome: 'Isabelle Souza Santos',
  cpf: '345.187.952-24',
  email: 'isabelle.souza@gmail.com',
  telefone: '(11)92456-2525'
}
```
**Códigos de Repostas**

| codigo | descricao
|-|-
| 200 | dados do curso retornados
| 404 | não existe cusuario com o id informado
| 500 | internal server error

---

### Editar usuario

`PUT` /profit/api/usuario/{id}

**Campos de Edição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| nome | texto | sim | o nome completo do usuário
| cpf | texto | sim | o cep do usuario deve conter 11 digitos
| email | email | sim | o email do usuário
| telefone | texto | sim | o telefone do usuario

**Exemplo de corpo de requisição**

```js
{
  nome: 'Isabelle Souza Santos',
  cpf: '345.187.952-24',
  email: 'isabelle.souza@gmail.com',
  telefone: '(11)92456-2525'
}
```

**Códigos de Repostas**

| codigo | descricao
|-|-
| 200 | usuario alterado com sucesso
| 400 | campos inválidos
| 404 | não existe curso com o id informado
| 408 | tempo esgotado
| 500 | internal server error

---
### Deletar usuario

`DELETE` /profit/api/usuario/{id}

**Campos**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| nome | texto | sim | o nome completo do usuário
| cpf | texto | sim | o cep do usuario deve conter 11 digitos
| email | email | sim | o email do usuário
| telefone | texto | sim | o telefone do usuario

**Exemplo de corpo de requisição**

```js
{
  nome: 'Isabelle Souza Santos',
  cpf: '345.187.952-24',
  email: 'isabelle.souza@gmail.com',
  telefone: '(11)92456-2525'
}
```

**Códigos de Repostas**

| codigo | descricao
|-|-
| 200 | usuario removido com sucesso
| 404 | não existe usuario com o id informado
| 408 | tempo esgotado
| 500 | internal server error

---
### Listar usuarios

`GET` /profit/api/usuarios

**Exemplo de corpo de requisição**

```js
{
  nome: 'Isabelle Souza Santos',
  cpf: '345.187.952-24',
  email: 'isabelle.souza@gmail.com',
  telefone: '(11)92456-2525'
}
```
```js
{
  nome: 'Diego Martins Dias',
  cpf: '457.125.632-89',
  email: 'di.martins@hotmail.com',
  telefone: '(11)96572-5532'
}
```
**Códigos de Repostas**

| codigo | descricao
|-|-
| 200 | dados dos usuários retornados
| 404 | usuarios não encontrados
| 401 | sem permissão
| 500 | internal server error