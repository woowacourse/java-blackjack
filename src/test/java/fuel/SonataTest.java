package fuel;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class SonataTest {

    @Test
    void generate_test() {
        assertThatCode(() -> new Sonata(130))
                .doesNotThrowAnyException();
    }

    @Test
    void getChargeQuantity_Test() {
        // given
        int distance = 150;

        // when
        Car car = new Sonata(distance);

        // then
        assertThat(car.getChargeQuantity()).isEqualTo(15);
    }
}