package co.edu.unbosque.GiraldoSantiago_Prog2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
 import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Colombiano {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer cedula;
	private String nombre;
	@ManyToOne
	@JoinColumn(name="idPaquetes")
	private Paquetes idPaquetes;
	
	/**
	 * 
	 */
	public Colombiano() {
	
	}
	
	public Integer getCedula() {
		return cedula;
	}
	
	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Paquetes getIdPaquetes() {
		return idPaquetes;
	}

	public void setIdPaquetes(Paquetes idPaquetes) {
		this.idPaquetes = idPaquetes;
	}
	

	
}

	


