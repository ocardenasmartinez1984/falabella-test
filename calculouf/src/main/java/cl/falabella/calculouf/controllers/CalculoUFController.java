package cl.falabella.calculouf.controllers;

import cl.falabella.calculouf.services.CalculoUFService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Capa de presentacion (controller).
 *
 * <p>Expone el endpoint HTTP que recibe la cantidad de UF y delega el
 * calculo en la capa de negocio, devolviendo el valor total sin decimales.</p>
 */
@RestController
@RequiredArgsConstructor
public class CalculoUFController {

    /** Servicio de negocio que realiza el calculo del valor de las UF. */
    private final CalculoUFService calculoUFService;

    /**
     * Endpoint GET que calcula el valor total de una cantidad de UF.
     *
     * <p>Ejemplo: {@code GET /calculouf?ufs=3}</p>
     *
     * @param ufs cantidad de UF a calcular
     * @return respuesta HTTP 200 con el resultado de la multiplicacion sin decimales
     */
    @GetMapping(value = "/calculouf", produces = "application/json")
    public ResponseEntity<Long> getCalculoUF(@RequestParam("ufs") Integer ufs) {
        long resultado = calculoUFService.calcular(ufs);
        return ResponseEntity.ok(resultado);
    }
}
