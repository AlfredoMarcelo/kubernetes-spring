package org.supanta.springcloud.msvc.cursos.services;

import org.supanta.springcloud.msvc.cursos.entity.Curso;

import java.util.List;
import java.util.Optional;
//se hace manual, aqui se crean los metodos que se implementaran para la clase, en ServiceImpl se inyectaran
public interface CursoService {

  List<Curso> listar();             // listar los cursos
  Optional<Curso> porId(Long id);   // buscar curso por id
  Curso guardar(Curso curso);       // para gurdar nuevo y actualizar
  void eliminar(Long id);           // es void porque no retorna nada solo hace el query para eliminar en la bd

}
