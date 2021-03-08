package fuel;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class AvanteTest {

    @Test
    void avante_generate_test() {
        assertThatCode(() -> new Avante(130))
                .doesNotThrowAnyException();
    }

    @Test
    void getChargeQuantity_Test() {
        // given
        int distance = 150;

        // when
        Car car = new Avante(distance);

        // then
        assertThat(car.getChargeQuantity()).isEqualTo(10);
    }
}