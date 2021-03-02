package fuel;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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