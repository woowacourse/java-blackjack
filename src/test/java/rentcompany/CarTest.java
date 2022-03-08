package rentcompany;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

public class CarTest {

    @Test
    void createSonata() {
        int tripDistance = 100;
        assertThatCode(() -> new Sonata(tripDistance))
                .doesNotThrowAnyException();
    }

    @Test
    void createAvante() {
        int tripDistance = 100;
        assertThatCode(() -> new Avante(tripDistance))
                .doesNotThrowAnyException();
    }

    @Test
    void createK5() {
        int tripDistance = 100;
        assertThatCode(() -> new K5(tripDistance))
                .doesNotThrowAnyException();
    }
}
