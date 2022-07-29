package org.supanta.springcloud.msvc.usuarios.services;

import org.supanta.springcloud.msvc.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

  List<Usuario> listar();           // search todos los users
  Optional<Usuario> porId(Long id); // search  por id
  Usuario guardar(Usuario usuario);
  void eliminar(Long id);           // para eliminar por id

}
