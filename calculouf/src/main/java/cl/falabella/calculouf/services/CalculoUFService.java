package cl.falabella.calculouf.services;

import cl.falabella.calculouf.data.MonedaData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Capa de negocio (service).
 *
 * <p>Obtiene el valor de la UF a traves de la capa de datos y lo
 * multiplica por la cantidad indicada, devolviendo el resultado sin
 * decimales.</p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CalculoUFService {

    /** Capa de datos que provee el valor actual de la UF. */
    private final MonedaData monedaData;

    /**
     * Calcula el valor total de una cantidad de UF.
     *
     * @param cantidadUf cantidad de UF a multiplicar por el valor actual
     * @return el resultado {@code valorUf * cantidadUf} redondeado sin decimales
     */
    public long calcular(int cantidadUf) {
        double valorUf = monedaData.getValorUf();
        long resultado = Math.round(valorUf * cantidadUf);
        log.info("Calculo: {} UF * {} = {}", valorUf, cantidadUf, resultado);
        return resultado;
    }
}
