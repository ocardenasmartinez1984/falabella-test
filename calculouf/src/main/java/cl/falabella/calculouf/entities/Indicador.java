package cl.falabella.calculouf.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Representa un indicador economico individual.
 *
 * <p>Por ejemplo, la UF:</p>
 * <pre>
 * { "date": "2026-09-07", "value": 40883 }
 * </pre>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Indicador {

    /** Fecha de vigencia del indicador (formato yyyy-MM-dd). */
    private String date;

    /** Valor del indicador (por ejemplo, el valor de la UF en pesos). */
    private double value;
}
