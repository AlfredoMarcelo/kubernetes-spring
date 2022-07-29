package org.supanta.springcloud.msvc.usuarios.repositories;

import org.springframework.data.repository.CrudRepository;
import org.supanta.springcloud.msvc.usuarios.models.entity.Usuario;

// la interface debe extender de CrudRepository, se debe agregar entre diamantes la clase y el data type del ID

public interface UsuarioRepository extends CrudRepository <Usuario , Long> {
}
