package com.procesos.concesionario.controllers;

import com.procesos.concesionario.models.User;
import com.procesos.concesionario.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private UserServiceImp userService;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody User user){
        Map response= new HashMap();
        try{
            response.put("message","Usuario logueado");
            response.put("token",userService.login(user));
            return new ResponseEntity(response, HttpStatus.OK);
        }catch (Exception e){
            response.put("message","Usuario no encontrado");//borrar esta linea
            response.put("data",e.getMessage());//data por message
            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

}
