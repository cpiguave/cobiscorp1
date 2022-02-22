package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.javaguides.springboot.service.LibroService;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Libro;
import net.javaguides.springboot.repository.LibroRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class LibroController {

	@Autowired
	private LibroRepository libroRepository;

	@GetMapping("/libros")
	public List<Libro> getAllLibros() {
		return libroRepository.findAll();
	}

	@Autowired
	private LibroService libroService;

	@GetMapping("/paginalibros")
	public ResponseEntity<Page<Libro>> paginas(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "nombre") String order,
			@RequestParam(defaultValue = "true") boolean asc) {
		Page<Libro> libro = libroService.paginas(
				PageRequest.of(page, size, Sort.by(order)));
		if (!asc)
			libro = libroService.paginas(
					PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<Libro>>(libro, HttpStatus.OK);
	}

	// create employee rest api
	@PostMapping("/libros")
	public Libro createLibro(@RequestBody Libro libro) {
		return libroRepository.save(libro);
	}

	// get employee by id rest api
	@GetMapping("/libros/{id}")
	public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
		Libro libro = libroRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Libro no existe con id :" + id));
		return ResponseEntity.ok(libro);
	}

	// update employee rest api

	@PutMapping("/libros/{id}")
	public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libroDetails) {
		Libro libro = libroRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Libro no existe con id :" + id));

		libro.setNombre(libroDetails.getNombre());
		libro.setDescripcion(libroDetails.getDescripcion());
		libro.setAutor(libroDetails.getAutor());
		libro.setFecha_publicacion(libroDetails.getFecha_publicacion());
		libro.setNumero_ejemplares(libroDetails.getNumero_ejemplares());
		libro.setCosto(libroDetails.getCosto());

		Libro updatedEmployee = libroRepository.save(libro);
		return ResponseEntity.ok(updatedEmployee);
	}

	// delete employee rest api
	@DeleteMapping("/libros/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteLibro(@PathVariable Long id) {
		Libro libro = libroRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Libro no existe con id :" + id));

		libroRepository.delete(libro);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
