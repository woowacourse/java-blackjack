package rent;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import rent.car.Car;
import rent.car.Sonata;

public class ConcreteCarTest {

    private Car createCar(int distance) {
        return new Sonata(distance);
    }

    @DisplayName("여행거리로 자동차를 생성한다.")
    @Test
    public void createCarWithDistance() {
        //given
        int distance = 100;

        //when
        Car car = createCar(distance);

        //then
        assertThat(car).isNotNull();
    }

    @DisplayName("주입해야할 연료량을 구한다.")
    @Test
    public void testChargeQuantity() {
        //given
        int distance = 100;
        Car car = createCar(distance);

        //when
        double chargeQuantity = car.getChargeQuantity();

        //then
        assertThat(chargeQuantity).isEqualTo(10.0);
    }
}
