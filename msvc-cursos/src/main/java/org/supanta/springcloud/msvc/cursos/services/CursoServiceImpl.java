package org.supanta.springcloud.msvc.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supanta.springcloud.msvc.cursos.entity.Curso;
import org.supanta.springcloud.msvc.cursos.repositories.CursoRepository;

import java.util.List;
import java.util.Optional;

@Service  // con esto se registra como componente de spring
public class CursoServiceImpl implements CursoService{

  @Autowired
  private CursoRepository repository;


  @Override
  @Transactional(readOnly = true)     //readonly=true, para que sea solo lectura
  public List<Curso> listar() {
    return (List<Curso>) repository.findAll(); //se castea a una List, porque finAll retorna un iterable
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Curso> porId(Long id) {
    return repository.findById(id);
  }

  @Override
  @Transactional
  public Curso guardar(Curso curso) {
    return repository.save(curso);
  }

  @Override
  @Transactional
  public void eliminar(Long id) {
  repository.deleteById(id);
  }
}
