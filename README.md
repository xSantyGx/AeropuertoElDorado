<!--Heading-->
	
   #  Santiago Giraldo
   # Programa envio de paquetes aeropuerto el dorado:  [![Logo.png](https://i.postimg.cc/0QKp2pFy/Logo.png)](https://postimg.cc/mzRcmFTv)

## *Programa escrito en java, utilizando SpringBoot creado para generar y manipular bases de datos*
 >software creado por Santiago Giraldo 
---

<!--List-->
## **Características**
---

*Insertar Datos:
* >Cedula de la persona que lo recibe en bogotá
* >Tamaño Volumetrico  (Fuera de rango es ilegal)


*Base de datos personas colombianas: 
* >Sistema que permita agregar ciudadanos (CRUD)
* >Nombre
* >Cedula

*Base de datos paquetes: (Ruta-Estado-idPersonas-Tamaño)
* >Si fuera de fecha o fuera de rango (Confiscado)
* >sino (aceptado)
* >Origen de paquete


## Base de datos
---
### *La base de datos es similar a la presentada en el siguiente panel:*
|Id     |  Nombre  |  Cédula  |   estado    |  lugar    | 
| ----------|:---------: |---------:| --------- |:----------:|  
|    1  |     Diego |  12386457|   confiscado      |  Francia |
|    2 |  Juan |  16435019|    aceptado     |  Bosnia |
|    3   |    Camilo   | 13652491 |    confiscado    | Ecuador|
---


# Metodos del programa

*  Metodo utilizado para agregar  a la lista
    ``` java
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
    ```
* Metodo para mostrar la lista
    ``` java
    @GetMapping("/Envios/mostrarTodo")
	public ResponseEntity<List<Colombiano>> mostrarTodo(){
		List<Colombiano> lista = (List<Colombiano>) opresp.findAll();
		if(lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	}
    ```
* Metodo para eliminar mediante la cedula

    ``` java
   
        @DeleteMapping("/Envios/eliminar{cedula}")
	public ResponseEntity<String> eliminarPorId(@RequestParam Integer cedula){
		Optional<Colombiano> dato = opresp.findByCedula(cedula);
		if(dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar el dato.");
		}
		opresp.deleteByCedula(cedula);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Dato eliminado con exito.");
	}

    ```
 * Metodo actualizar mediante la cedula
   ``` java
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
    ```
#  Universidad el Bosque
  > [Universidad el Bosque]( https://www.unbosque.edu.co )
