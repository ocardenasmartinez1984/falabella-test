package cl.falabella.calculouf.services;

import cl.falabella.calculouf.data.MonedaData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Pruebas unitarias de la capa de negocio {@link CalculoUFService}.
 *
 * <p>Se mockea {@link MonedaData} para probar la logica de multiplicacion
 * y redondeo de forma aislada, sin acceder al endpoint externo.</p>
 */
@ExtendWith(MockitoExtension.class)
class CalculoUFServiceTest {

    @Mock
    private MonedaData monedaData;

    @InjectMocks
    private CalculoUFService calculoUFService;

    @Test
    void calcular_multiplicaValorUfPorCantidad() {
        when(monedaData.getValorUf()).thenReturn(40883.0);

        long resultado = calculoUFService.calcular(3);

        assertThat(resultado).isEqualTo(122649L);
    }

    @Test
    void calcular_conUnaUf_devuelveElValorUf() {
        when(monedaData.getValorUf()).thenReturn(40883.0);

        long resultado = calculoUFService.calcular(1);

        assertThat(resultado).isEqualTo(40883L);
    }

    @Test
    void calcular_redondeaSinDecimales() {
        // 40883.6 * 2 = 81767.2 -> redondea a 81767
        when(monedaData.getValorUf()).thenReturn(40883.6);

        long resultado = calculoUFService.calcular(2);

        assertThat(resultado).isEqualTo(81767L);
    }

    @Test
    void calcular_conCero_devuelveCero() {
        when(monedaData.getValorUf()).thenReturn(40883.0);

        long resultado = calculoUFService.calcular(0);

        assertThat(resultado).isZero();
    }
}
