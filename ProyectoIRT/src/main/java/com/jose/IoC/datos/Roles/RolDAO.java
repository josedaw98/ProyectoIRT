package com.jose.IoC.datos.Roles;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface RolDAO extends CrudRepository<Rol,String> {

	
}
