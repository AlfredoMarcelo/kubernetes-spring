package org.supanta.springcloud.msvc.usuarios.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.supanta.springcloud.msvc.usuarios.models.entity.Usuario;
import org.supanta.springcloud.msvc.usuarios.services.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

  @Autowired
  private UsuarioService service;

  @GetMapping //"/" este será el path por defecto
  public List<Usuario> listar(){
    return service.listar();  //se convertira en json gracias a @RestController y @ResponseBody
  }

  @GetMapping("/{id}") // {id}, es un pathvariable
  public ResponseEntity<?> detalle(@PathVariable Long id, Model model){ //? es generico, puede ser Usuario o sin contenido
    Optional<Usuario> usuarioOptional = service.porId(id);
    if (usuarioOptional.isPresent()){
      return ResponseEntity.ok().body(usuarioOptional.get());//devuelve un code 200 y el usuario, .ok()= 200, body=lo que contendra el json
    }
    return ResponseEntity.notFound().build();   //genera una respuesta con code http 404
  }


  @PostMapping()
  //@ResponseStatus(HttpStatus.CREATED)         //sirve para retornar la respuesta de la solicitud,201
  public ResponseEntity<?> crear(@RequestBody Usuario usuario){
    return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
  }

  @PutMapping("/{id}")            //@RequesBody, pide un usuario en json || @PathVariable recibe el id del path
  public ResponseEntity<?> editar(@RequestBody Usuario usuario, @PathVariable Long id){
    Optional<Usuario> usuarioOptional = service.porId(id);
    if(usuarioOptional.isPresent()){
      Usuario usuarioDb = usuarioOptional.get(); //guardamos el usuario retornado de la bd y se guarda en una Var de tipo Usuario
      usuarioDb.setNombre(usuario.getNombre());     // se setea el nombre, con el valor del usuario entrante
      usuarioDb.setEmail(usuario.getEmail());       // se setea el email, con el valor del usuario entrante
      usuarioDb.setPassword(usuario.getPassword()); // se setea el password, con el valor del usuario entrante
      return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb)); //se envia 200 y el body con usuario seteado
    }
    return ResponseEntity.notFound().build(); // 404, en caso de que no venga un usuario o haya errores en la solicitud
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> eliminar(@PathVariable Long id){
    Optional<Usuario> usuarioOptional = service.porId(id); //se utiliza porId para validar si esta o no y luego se llama al metodo para eliminar
    if(usuarioOptional.isPresent()){
      service.eliminar(id);                      // .noContent()= 204 se usa porque no se retorna nada solo se aplica la query en la bd
      return ResponseEntity.noContent().build(); // .build(), construye la respuesta
    }
    return ResponseEntity.notFound().build();
  }

}
