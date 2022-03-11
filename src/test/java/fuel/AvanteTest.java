package fuel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AvanteTest {

    @Test
    @DisplayName("Avante 차량 객체의 이름은 Avante이다.")
    void avante_get_name() {
        Car car = new Avante(100);

        assertThat(car.getName()).isEqualTo("Avante");
    }

    @Test
    @DisplayName("Avante 차량의 이동거리를 반환한다.")
    void avante_trip_distance() {
        Car car = new Avante(100);

        assertThat(car.getTripDistance()).isEqualTo(100);
    }

    @Test
    @DisplayName("Avante 차량의 연비를 반환한다.")
    void avante_distance_per_liter() {
        Car car = new Avante(100);

        assertThat(car.getDistancePerLiter()).isEqualTo(15);
    }

    @Test
    @DisplayName("Avante 차량의 연료 주입량을 반환한다.")
    void avante_charge_quantity() {
        Car car = new Avante(150);

        assertThat(car.getChargeQuantity()).isEqualTo(10);
    }
}
