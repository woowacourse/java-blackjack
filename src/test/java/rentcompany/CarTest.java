package rentcompany;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    @DisplayName("자동차 생성 테스트")
    @Test
    void creteCarTest() {
        Car car = new Sonata(150);
        assertThat(car).isNotNull();
    }

    @DisplayName("필요 주유량 테스트")
    @Test
    void getChangeQuantity_travels150_need10liter() {
        Car car = new Sonata(150);
        assertThat(car.getChargeQuantity()).isEqualTo(15);
    }
}
