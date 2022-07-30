package org.supanta.springcloud.msvc.cursos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.supanta.springcloud.msvc.cursos.entity.Curso;
import org.supanta.springcloud.msvc.cursos.services.CursoService;

import java.util.List;
import java.util.Optional;

@RestController
public class CursoController {

  @Autowired
  private CursoService service;

  @GetMapping
  public ResponseEntity<List<Curso>> listar(){
    return ResponseEntity.ok().body(service.listar());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> porId(@PathVariable Long id){  //en esta si utilizamos el ? porque habra dos respuestas
    Optional<Curso> cursoOp = service.porId(id);
    if(cursoOp.isPresent()){
      return ResponseEntity.ok(cursoOp.get());            //cuando exista el Curso de Id, 200 y retorna un Curso
    }
    return ResponseEntity.notFound().build();             // Cuando no exista el curso un 404
  }



}
