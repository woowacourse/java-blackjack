package fuel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SonataTest {

    @Test
    @DisplayName("Sonata 차량 객체의 이름은 Sonata이다.")
    void sonata_get_name() {
        Car car = new Sonata(100);

        assertThat(car.getName()).isEqualTo("Sonata");
    }

    @Test
    @DisplayName("Sonata 차량의 이동거리를 반환한다.")
    void sonata_trip_distance() {
        Car car = new Sonata(100);

        assertThat(car.getTripDistance()).isEqualTo(100);
    }

    @Test
    @DisplayName("Sonata 차량의 연비를 반환한다.")
    void sonata_distance_per_liter() {
        Car car = new Sonata(100);

        assertThat(car.getDistancePerLiter()).isEqualTo(10);
    }

    @Test
    @DisplayName("Sonata 차량의 연료 주입량을 반환한다.")
    void sonata_charge_quantity() {
        Car car = new Sonata(100);

        assertThat(car.getChargeQuantity()).isEqualTo(10);
    }
}
