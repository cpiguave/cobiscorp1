package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Libro;
import net.javaguides.springboot.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LibroService {
    @Autowired
    LibroRepository libroRepository;

    public Page<Libro> paginas(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }
}
