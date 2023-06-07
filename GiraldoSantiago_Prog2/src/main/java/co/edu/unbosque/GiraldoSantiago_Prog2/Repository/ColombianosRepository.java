package co.edu.unbosque.GiraldoSantiago_Prog2.Repository;

import java.util.List;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.GiraldoSantiago_Prog2.Model.*;

public interface ColombianosRepository extends CrudRepository<Colombiano, Integer>{



// public void deleteByNumero1(double numero1);
	
	public Optional<Colombiano> findById(Integer id);
	
	public Optional<Colombiano> findByCedula(Integer cedula);
	
	public void deleteByCedula(Integer cedula);
	
//	public Optional<List<Envios>> findByNumero1(double numero1);
	
	public List<Colombiano> findAll();
	
//	public  List<Paquetes> findAllByIdPaquetes(Paquetes paquetes);
	
}
