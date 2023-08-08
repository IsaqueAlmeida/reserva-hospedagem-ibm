# Reserva de Hospedagem - IBM

Bem-vindo ao projeto de Reserva de Hospedagem IBM! Este projeto é um exemplo de aplicação de reserva de hospedagem, onde os usuários podem criar, visualizar, atualizar e excluir reservas de hospedagem.

## Funcionalidades

- Criação de novas reservas de hospedagem.
- Visualização de detalhes de uma reserva pelo seu ID.
- Listagem de todas as reservas de hospedagem.
- Atualização de informações de uma reserva existente.
- Cancelamento de uma reserva.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring MVC
- Lombok
- H2 Database (para fins de teste)
- Mockito (para testes unitários)

## Configuração

1. Clone este repositório para a sua máquina local.
2. Certifique-se de ter o Java JDK instalado.
3. Importe o projeto em sua IDE preferida.
4. Execute o aplicativo Spring Boot.
5. Acesse a aplicação através do navegador ou ferramenta de API (como o Postman) usando as rotas definidas no `ReservaController`.

## Estrutura do Projeto

- `src/main/java/com/example/reservahospedagemibm/`
    - `controller`: Contém os controladores da API REST.
    - `domain`: Contém a classe `Reserva` que define a entidade de reserva de hospedagem.
    - `dto`: Contém a classe `ReservaDto` que define o objeto de transferência de dados.
    - `repository`: Contém a interface `ReservaRepository` para acesso aos dados.
    - `service`: Contém a classe `ReservaService` que implementa a lógica de negócios.
    - `service/exception`: Contém a classe `ReservaNotFoundException` para exceções personalizadas.
    - `controller/exceptions`: Contém a classe `StandartError` para lidar com erros.
    - `controller/exceptions`: Contém o controlador de exceções `ControllerExceptionHandler`.

## Testes

Os testes unitários são implementados para verificar a funcionalidade das classes de serviço e controlador.
