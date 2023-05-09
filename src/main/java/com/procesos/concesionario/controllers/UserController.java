package com.procesos.concesionario.controllers;

import com.procesos.concesionario.models.User;
import com.procesos.concesionario.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserServiceImp userService;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity getById (@PathVariable(name = "id") Long id) {
        Map response= new HashMap();
        try{
            response.put("message","se encontro el usuario");
            response.put("data",userService.getUserById(id));
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("message","no se encontro el usuario");
            response.put("data",e.getMessage());//userService.getUserById(id) //Optional.Empty //"e.getMessage()"
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/user")
    public ResponseEntity createUser(@RequestBody User user){
        Map response= new HashMap();
        try{
            response.put("message","se gurado el usuario");
            response.put("data",userService.createUser(user));
            return new ResponseEntity(response, HttpStatus.CREATED);
        }catch (Exception e){
            response.put("message","no se guardo el usuario");
            response.put("data",e.getMessage());//userService.getUserById(id) //Optional.Empty //"e.getMessage()"
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users")
    public ResponseEntity getAllUser (){
        Map response= new HashMap();
        try{
            response.put("message","se encontraron los usuarios");
            response.put("data",userService.allUser());
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("message","no se encontraron los usuarios");
            response.put("data",e.getMessage());//userService.getUserById(id) //Optional.Empty //"e.getMessage()"
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping  (value = "/update/{id}")
    public ResponseEntity editUser (@PathVariable(name = "id") Long id,@RequestBody User user){
        Map response= new HashMap();
        try{
            response.put("message","usuario editado correctamente");
            response.put("data",userService.updateUser(id,user));
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("message","usuario no editado correctamente");
            response.put("data",userService.getUserById(id));//userService.getUserById(id) //Optional.Empty //"e.getMessage()"
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

}
