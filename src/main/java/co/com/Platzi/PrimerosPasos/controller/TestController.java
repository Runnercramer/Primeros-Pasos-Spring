package co.com.Platzi.PrimerosPasos.controller;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @RequestMapping(value="/root")
    @ResponseBody
    public ResponseEntity<String> function(){
        return new ResponseEntity<>("Hello from controller", HttpStatus.OK);
    }
}
