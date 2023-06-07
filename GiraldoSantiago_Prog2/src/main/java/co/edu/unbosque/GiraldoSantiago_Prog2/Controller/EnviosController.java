package co.edu.unbosque.GiraldoSantiago_Prog2.Controller;

import java.util.List;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.GiraldoSantiago_Prog2.Model.Colombiano;
import co.edu.unbosque.GiraldoSantiago_Prog2.Repository.ColombianosRepository;
import co.edu.unbosque.GiraldoSantiago_Prog2.Repository.PaquetesRepository;
import co.edu.unbosque.GiraldoSantiago_Prog2.Model.Paquetes;
import jakarta.transaction.Transactional;

@Transactional
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Santiagoapi")
public class EnviosController {
	@Autowired
	private ColombianosRepository opresp;
	@Autowired
	private PaquetesRepository paqrep;
	
	@PostMapping("/Envios/Agregar")
	public ResponseEntity<String> agregar(@RequestParam Integer cedula,@RequestParam String nombre,@RequestParam String lugar,@RequestParam double largo,@RequestParam double ancho,@RequestParam double alto){
		Colombiano temp = new Colombiano();
		
		temp.setCedula(cedula);
		temp.setNombre(nombre);
		Paquetes paquetes = new Paquetes();
		paquetes.setLugar(lugar);
		
		paquetes.setTamanio((largo*ancho*alto)/5000);
		if(paquetes.getTamanio()>30) {
			paquetes.setEstado("confiscado");
		}
		else{
		paquetes.setEstado("aceptado");
		}
		temp.setIdPaquetes(paquetes);
		
		opresp.save(temp);
		paqrep.save(paquetes);
		return ResponseEntity.status(HttpStatus.CREATED).body("Dato creado con exito: 201");
		
	}

	
//	@GetMapping("/ListarEmpleados")
//	public String getEmpleados(Model model) {
//		List<Colombiano> all = opresp.findAll();
//		model.addAttribute("colombiano", all);
//		return "colombiano.html";
//	}
	
//	@GetMapping("/ListarEmpleados")
//	public String getColombiano(Model model) {
//		return "colombiano.html";
//	}
	
	@GetMapping("/Envios/mostrarTodo")
	public ResponseEntity<List<Colombiano>> mostrarTodo(){
		List<Colombiano> lista = (List<Colombiano>) opresp.findAll();
		if(lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	}
	
	@GetMapping("/Envios/mostrar{cedula}")
	public ResponseEntity<Optional<Colombiano>> mostrarPorID(@RequestParam Integer cedula){
		Optional<Colombiano> dato = opresp.findByCedula(cedula);
		if(dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(dato);
	}
	
	@DeleteMapping("/Envios/eliminar{cedula}")
	public ResponseEntity<String> eliminarPorId(@RequestParam Integer cedula){
		Optional<Colombiano> dato = opresp.findByCedula(cedula);
		if(dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar el dato.");
		}
		opresp.deleteByCedula(cedula);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Dato eliminado con exito.");
	}
	
	@PutMapping("/Envios/actualizar{cedula}")
	public ResponseEntity<String> actulizarPorId(@RequestParam Integer cedula,@RequestParam Integer cedulaNueva,@RequestParam String nombre, @RequestParam Integer volumetric){
		Optional<Colombiano> dato = opresp.findByCedula(cedula);
		
		if(dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dato no encontrado");
		}
		Colombiano temp = dato.get();
		temp.setCedula(cedulaNueva);
		temp.setNombre(nombre);
		opresp.save(temp);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Dato actualizado con exito.");
	}
	
//	@DeleteMapping("/operacion/numero1/{num1}")
//	public ResponseEntity<String> eliminarPorNum1(@RequestParam double num1){
//		Optional<List<Envios>> dato = opresp.findByNumero1(num1);
//		List<Envios> temp = dato.get();
//		if(temp.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar el dato.");
//		}
//		opresp.deleteByNumero1(num1);
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Dato eliminado con exito.");
//	}	
}