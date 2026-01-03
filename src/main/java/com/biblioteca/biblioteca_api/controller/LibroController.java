package com.biblioteca.biblioteca_api.controller;

import com.biblioteca.biblioteca_api.model.Libro;
import com.biblioteca.biblioteca_api.repositorio.LibroRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroRepositorio repository;

    public LibroController(LibroRepositorio repository) {
        this.repository = repository;
    }

    // GET /libros
    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodos() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    // GET /libros/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(libro -> new ResponseEntity<>(libro, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // GET /libros/buscar?autor=...&anio=...
    @GetMapping("/buscar")
    public ResponseEntity<List<Libro>> buscar(
            @RequestParam String autor,
            @RequestParam(required = false) Integer anio
    ) {
        List<Libro> resultado = repository.buscarPorAutorYAnio(autor, anio);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    // POST /libros
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevo = repository.save(libro);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }
}
