package cl.falabella.calculouf.controllers;

import cl.falabella.calculouf.services.CalculoUFService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Pruebas del endpoint HTTP {@link CalculoUFController} usando MockMvc.
 *
 * <p>Se mockea {@link CalculoUFService} para probar la capa web de forma
 * aislada.</p>
 */
@WebMvcTest(CalculoUFController.class)
class CalculoUFControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculoUFService calculoUFService;

    @Test
    void getCalculoUF_devuelveResultado() throws Exception {
        when(calculoUFService.calcular(3)).thenReturn(122649L);

        mockMvc.perform(get("/calculouf").param("ufs", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("122649"));
    }

    @Test
    void getCalculoUF_sinParametro_devuelveBadRequest() throws Exception {
        mockMvc.perform(get("/calculouf"))
                .andExpect(status().isBadRequest());
    }
}
