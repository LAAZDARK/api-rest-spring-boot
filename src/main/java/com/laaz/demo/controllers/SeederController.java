package com.laaz.demo.controllers;


import com.laaz.demo.services.SeederService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/seed")
public class SeederController {

        private final SeederService seederService;

        @GetMapping(path = "/execute")
        public ResponseEntity<?> executeSeed() {
            try {
                seederService.executeSeed();
                return ResponseEntity.ok("Seed ejecutado correctamente");
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("Error al ejecutar el seed: " + e.getMessage());
            }
        }
}
