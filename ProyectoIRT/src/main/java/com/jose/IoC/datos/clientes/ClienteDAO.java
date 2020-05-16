package com.jose.IoC.datos.clientes;

import org.springframework.data.repository.CrudRepository;

public interface ClienteDAO extends CrudRepository<Cliente, String> {

}
