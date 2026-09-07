package cl.falabella.calculouf.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Representa la respuesta del endpoint de indicadores economicos.
 *
 * <p>La estructura real del JSON es:</p>
 * <pre>
 * {
 *   "status": "success",
 *   "data": {
 *     "uf":   { "date": "2026-09-07", "value": 40883 },
 *     "dolar": { ... },
 *     ...
 *   }
 * }
 * </pre>
 *
 * <p>Los indicadores viven dentro del objeto {@code data}; el resto de
 * campos desconocidos se ignoran gracias a {@link JsonIgnoreProperties}.</p>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseIndicadores {

    /** Estado de la respuesta ("success" cuando la consulta es correcta). */
    private String status;

    /** Contenedor con los indicadores economicos. */
    private DataIndicadores data;

    /**
     * Objeto anidado que agrupa los indicadores economicos.
     * Solo se mapea la UF; el resto se ignora.
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataIndicadores {

        /** Indicador correspondiente a la Unidad de Fomento (UF). */
        private Indicador uf;
    }
}
