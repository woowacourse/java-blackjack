package rent;

import org.assertj.core.api.Assertions;
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
        Assertions.assertThat(car).isNotNull();
    }
}
