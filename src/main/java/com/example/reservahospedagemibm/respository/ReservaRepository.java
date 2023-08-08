package com.example.reservahospedagemibm.respository;

import com.example.reservahospedagemibm.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para a entidade Reserva.
 *
 * Esta interface estende JpaRepository, fornecendo métodos padrão de CRUD para a entidade Reserva.
 * Spring Data JPA irá automaticamente implementar esses métodos, permitindo fácil acesso aos dados.
 *
 * Os métodos definidos aqui incluem operações como salvar, excluir, buscar por ID e buscar todos os registros.
 *
 * Anotação @Repository indica que esta interface é um componente gerenciado pelo Spring, permitindo sua
 * injeção em outras partes do aplicativo que precisam acessar o banco de dados.
 */
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
