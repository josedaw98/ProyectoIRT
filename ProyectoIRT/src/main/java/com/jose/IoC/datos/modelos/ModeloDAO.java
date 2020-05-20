package com.jose.IoC.datos.modelos;

import org.springframework.data.repository.CrudRepository;

import com.jose.IoC.datos.marca.Marca;

public interface ModeloDAO extends CrudRepository<Modelo, Integer> {

}
