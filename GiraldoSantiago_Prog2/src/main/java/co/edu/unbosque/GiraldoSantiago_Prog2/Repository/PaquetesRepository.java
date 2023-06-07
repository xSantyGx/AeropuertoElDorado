package co.edu.unbosque.GiraldoSantiago_Prog2.Repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.unbosque.GiraldoSantiago_Prog2.Model.Colombiano;
import co.edu.unbosque.GiraldoSantiago_Prog2.Model.Paquetes;

public interface PaquetesRepository extends CrudRepository<Paquetes, Integer> {
	
	public void deleteById(Integer id);

}
