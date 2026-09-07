package cl.falabella.calculouf.data;

import cl.falabella.calculouf.entities.ApiResponseIndicadores;
import cl.falabella.calculouf.entities.ApiResponseIndicadores.DataIndicadores;
import cl.falabella.calculouf.entities.Indicador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias de la capa de datos {@link MonedaData}.
 *
 * <p>Se mockea {@link RestTemplate} para simular la respuesta del endpoint
 * externo sin realizar llamadas de red reales.</p>
 */
@ExtendWith(MockitoExtension.class)
class MonedaDataTest {

    private static final String URL = "https://api.boostr.cl/economy/indicators.json";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MonedaData monedaData;

    @BeforeEach
    void setUp() {
        // Inyecta el valor del campo anotado con @Value("${url.api}")
        ReflectionTestUtils.setField(monedaData, "url", URL);
    }

    @Test
    void getValorUf_devuelveElValorDeLaUf() {
        ApiResponseIndicadores respuesta = construirRespuesta(40883.0);
        when(restTemplate.getForObject(URL, ApiResponseIndicadores.class)).thenReturn(respuesta);

        double valor = monedaData.getValorUf();

        assertThat(valor).isEqualTo(40883.0);
    }

    @Test
    void getValorUf_respuestaNula_lanzaExcepcion() {
        when(restTemplate.getForObject(URL, ApiResponseIndicadores.class)).thenReturn(null);

        assertThatThrownBy(() -> monedaData.getValorUf())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("No se pudo obtener el valor de la UF");
    }

    @Test
    void getValorUf_sinData_lanzaExcepcion() {
        ApiResponseIndicadores respuesta = new ApiResponseIndicadores();
        respuesta.setStatus("success");
        // data == null
        when(restTemplate.getForObject(URL, ApiResponseIndicadores.class)).thenReturn(respuesta);

        assertThatThrownBy(() -> monedaData.getValorUf())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void getValorUf_sinUf_lanzaExcepcion() {
        ApiResponseIndicadores respuesta = new ApiResponseIndicadores();
        respuesta.setStatus("success");
        respuesta.setData(new DataIndicadores()); // uf == null
        when(restTemplate.getForObject(URL, ApiResponseIndicadores.class)).thenReturn(respuesta);

        assertThatThrownBy(() -> monedaData.getValorUf())
                .isInstanceOf(IllegalStateException.class);
    }

    private ApiResponseIndicadores construirRespuesta(double valorUf) {
        Indicador uf = new Indicador();
        uf.setDate("2026-09-07");
        uf.setValue(valorUf);

        DataIndicadores data = new DataIndicadores();
        data.setUf(uf);

        ApiResponseIndicadores respuesta = new ApiResponseIndicadores();
        respuesta.setStatus("success");
        respuesta.setData(data);
        return respuesta;
    }
}
