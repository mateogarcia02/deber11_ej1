package com.biblioteca.biblioteca_api.repositorio;

import com.biblioteca.biblioteca_api.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class LibroRepositorio {

    private List<Libro> libros = new ArrayList<>();
    private Long contadorId = 1L;

    public List<Libro> findAll() {
        return libros;
    }

    public Optional<Libro> findById(Long id) {
        return libros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }

    public List<Libro> buscarPorAutorYAnio(String autor, Integer anio) {
        return libros.stream()
                .filter(l -> l.getAutor().equalsIgnoreCase(autor))
                .filter(l -> anio == null || l.getAnioPublicacion() == anio)
                .collect(Collectors.toList());
    }

    public Libro save(Libro libro) {
        libro.setId(contadorId++);
        libros.add(libro);
        return libro;
    }
}
