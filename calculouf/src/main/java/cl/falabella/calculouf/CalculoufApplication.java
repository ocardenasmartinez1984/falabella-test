package cl.falabella.calculouf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Clase principal del microservicio CalculoUF.
 *
 * <p>Arranca el contexto de Spring Boot y expone los beans compartidos
 * de la aplicacion. La aplicacion sigue una arquitectura de tres capas:
 * controller -> service -> data.</p>
 */
@SpringBootApplication
public class CalculoufApplication {

	/**
	 * Punto de entrada de la aplicacion.
	 *
	 * @param args argumentos de linea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(CalculoufApplication.class, args);
	}

	/**
	 * Bean de {@link RestTemplate} utilizado por la capa de datos para
	 * consumir el endpoint externo de indicadores economicos.
	 *
	 * @return instancia de RestTemplate gestionada por Spring
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
