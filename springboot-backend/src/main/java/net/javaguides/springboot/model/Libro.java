package net.javaguides.springboot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tcobis_libro")
public class Libro implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "lib_id", unique = true, nullable = false)
	private Long id;

	@Basic(optional = false)
	@Column(name = "lib_nombre", unique = true, nullable = false)
	@Size(max = 150)
	private String nombre;

	@Basic(optional = false)
	@Column(name = "lib_descripcion")
	@Size(max = 300)
	private String descripcion;

	// @Basic(optional = false)
	@Column(name = "lib_autor")
	@Size(max = 150)
	private String autor;

	@Basic(optional = false)
	@Column(name = "lib_fecha_publicacion")
	@Temporal(TemporalType.DATE)
	private Date fecha_publicacion = new Date();

	@Basic(optional = false)
	@Column(name = "lib_numero_ejemplares")
	private int numero_ejemplares;

	// @Basic(optional = false)
	@Column(name = "lib_costo", scale = 4)
	private Double costo;

	public Libro() {
	}

	public Libro(Long id, String nombre, String descripcion, String autor, Date fecha_publicacion,
			int numero_ejemplares, Double costo) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.autor = autor;
		this.fecha_publicacion = fecha_publicacion;
		this.numero_ejemplares = numero_ejemplares;
		this.costo = costo;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getFecha_publicacion() {
		return this.fecha_publicacion;
	}

	public void setFecha_publicacion(Date fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public int getNumero_ejemplares() {
		return this.numero_ejemplares;
	}

	public void setNumero_ejemplares(int numero_ejemplares) {
		this.numero_ejemplares = numero_ejemplares;
	}

	public Double getCosto() {
		return this.costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

}
