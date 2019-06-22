package com.ims.inventory.controllers;

import com.ims.inventory.dtos.ResponseDto;
import com.ims.inventory.models.SuperAdmin;
import com.ims.inventory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "super-admin")
@CrossOrigin("*")
public class SuperAdminController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "create-update", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> createOrUpdateUser(@RequestBody SuperAdmin user){
        ResponseDto responseDto = userService.createOrUpdateSuperAdmin(user);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> login(@RequestBody SuperAdmin user){
        ResponseDto responseDto = userService.loginSuperAdmin(user);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }
}
