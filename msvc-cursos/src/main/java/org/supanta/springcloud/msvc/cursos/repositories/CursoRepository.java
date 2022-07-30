package org.supanta.springcloud.msvc.cursos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.supanta.springcloud.msvc.cursos.entity.Curso;
//no se asigna la anotacion repository, esto se configura automaticamente como componente al extender de crudrepository
public interface CursoRepository extends CrudRepository<Curso, Long> {
}
