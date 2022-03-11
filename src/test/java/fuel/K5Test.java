package fuel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class K5Test {

    @Test
    @DisplayName("K5 차량 객체의 이름은 K5이다.")
    void k5_get_name() {
        Car car = new K5(100);

        assertThat(car.getName()).isEqualTo("K5");
    }

    @Test
    @DisplayName("K5 차량의 이동거리를 반환한다.")
    void k5_trip_distance() {
        Car car = new K5(100);

        assertThat(car.getTripDistance()).isEqualTo(100);
    }

    @Test
    @DisplayName("K5 차량의 연비를 반환한다.")
    void k5_distance_per_liter() {
        Car car = new K5(100);

        assertThat(car.getDistancePerLiter()).isEqualTo(13);
    }

    @Test
    @DisplayName("K5 차량의 연료 주입량을 반환한다.")
    void k5_charge_quantity() {
        Car car = new K5(130);

        assertThat(car.getChargeQuantity()).isEqualTo(10);
    }
}
