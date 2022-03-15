package fuelInjection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {

    @Test
    @DisplayName("K5 자동차 생성 테스트")
    void createValidCheckK5Car() {
        assertThat(new K5(260)).isNotNull();
    }

    @Test
    @DisplayName("아반때 자동차 생성 테스트")
    void createValidCheckAvanteCar() {
        assertThat(new Avante(260)).isNotNull();
    }

    @Test
    @DisplayName("소나타 자동차 생성 테스트")
    void createValidCheckSonataCar() {
        assertThat(new Sonata(260)).isNotNull();
    }

    @Test
    @DisplayName("자동차에 주입해야할 연료량 테스트")
    void checkChargeQuantity() {
        Car k5 = new K5(260);
        assertThat(k5.getChargeQuantity()).isEqualTo(20);
    }
}
