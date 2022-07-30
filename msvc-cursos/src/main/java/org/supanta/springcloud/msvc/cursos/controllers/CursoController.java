package org.supanta.springcloud.msvc.cursos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

  @PostMapping()
  public ResponseEntity<?> crear(@RequestBody Curso curso){                     // solicita un curso al json del cliente
    Curso cursoDb = service.guardar(curso);                                     // guarda en una variable la query junto al curso del client
    return ResponseEntity.status(HttpStatus.CREATED).body(cursoDb);             // retorna un 201 y el curso creado
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Curso curso){ //obtiene el valor id y el curso que viene en el json
    Optional<Curso> cursoOP = service.porId(id);                                    // guarda el curso que consigue con la query
    if(cursoOP.isPresent()){                                                        // si viene un curso se ejecuta
      Curso cursoDB = cursoOP.get();                                                // guarda el curso de la bs en una var
      cursoDB.setNombre(curso.getNombre());                                         // setea el nombre, con el valor del curso que viene
      return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cursoDB)); //retorna un 200 y hace la query con el curso seteado
    }
    return ResponseEntity.notFound().build();                                        // si no hay curso, devuelve un 404
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> eliminar(@PathVariable Long id){
    Optional<Curso> cursoOp = service.porId(id);
    if(cursoOp.isPresent()){
      service.eliminar(cursoOp.get().getId());
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }


}
