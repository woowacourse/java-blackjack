package fuel;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class K5Test {

    @Test
    void generate_test() {
        assertThatCode(() -> new K5(130))
                .doesNotThrowAnyException();
    }

    @Test
    void getChargeQuantity_Test() {
        // given
        int distance = 140;

        // when
        Car car = new K5(distance);

        // then
        assertThat(car.getChargeQuantity()).isEqualTo((double) distance / 13);
    }
}