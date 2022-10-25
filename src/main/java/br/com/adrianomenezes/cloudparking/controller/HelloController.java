package br.com.adrianomenezes.cloudparking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/")
@ApiIgnore
@Tag(name = "Hello Controller", description = "API Hello")
public class HelloController {

    @Operation(summary = "Hello Controller",ignoreJsonView = true)
    @GetMapping
    public String hello(){
        return "Hello pessoal";
    }
}
