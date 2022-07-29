package org.supanta.springcloud.msvc.usuarios.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supanta.springcloud.msvc.usuarios.models.entity.Usuario;
import org.supanta.springcloud.msvc.usuarios.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

  @Autowired
  private UsuarioRepository repository;

  @Override
  @Transactional(readOnly = true) //tiene que ser de springframework package
  public List<Usuario> listar() {
    return (List<Usuario>)repository.findAll(); //se castea con (List<>), para que devuelva un lista
  }

  @Override
  @Transactional(readOnly = true) //para que sea solo de lectura
  public Optional<Usuario> porId(Long id) {
    return repository.findById(id);
  }

  @Override
  @Transactional
  public Usuario guardar(Usuario usuario) {
    return repository.save(usuario); // para editar y insertar
  }

  @Override
  @Transactional
  public void eliminar(Long id) {
    repository.deleteById(id);
  }
}
