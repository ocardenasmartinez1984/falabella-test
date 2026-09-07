package cl.falabella.calculouf.data;

import cl.falabella.calculouf.entities.ApiResponseIndicadores;
import cl.falabella.calculouf.entities.Indicador;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Capa de acceso a datos (data).
 *
 * <p>Consume el endpoint externo de indicadores economicos y obtiene
 * el valor actual de la UF. Es la unica capa que conoce el detalle del
 * origen de los datos.</p>
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class MonedaData {

    /** Cliente HTTP inyectado para invocar el endpoint externo. */
    private final RestTemplate restTemplate;

    /** URL del endpoint de indicadores, configurada en application.properties. */
    @Value("${url.api}")
    private String url;

    /**
     * Obtiene el valor actual de la UF desde el endpoint de indicadores.
     *
     * @return el valor de la UF como {@code double}
     * @throws IllegalStateException si la respuesta es nula o no contiene la UF
     */
    public double getValorUf() {
        log.info("Consultando indicadores en {}", url);
        ApiResponseIndicadores respuesta = restTemplate.getForObject(url, ApiResponseIndicadores.class);

        if (respuesta == null || respuesta.getData() == null || respuesta.getData().getUf() == null) {
            throw new IllegalStateException("No se pudo obtener el valor de la UF desde el endpoint de indicadores");
        }

        Indicador uf = respuesta.getData().getUf();
        double valorUf = uf.getValue();
        log.info("Valor UF obtenido: {} (fecha {})", valorUf, uf.getDate());
        return valorUf;
    }
}
