package fuel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {

    @DisplayName("Car 구현체의 생성자를 호출하면, 객체가 생성된다.")
    @Test
    void constructor() {
        Car car = new Sonata(150);

        assertThat(car).isNotNull();
    }

    @DisplayName("Sonata 구현체 테스트")
    @Nested
    class SonataTest {
        @DisplayName("소나타의 이름은 언제나 Sonata 이다.")
        @ParameterizedTest(name = "[{index}] 거리: {0}")
        @ValueSource(ints = {100, 500, 3000})
        void getName_sonata(int distance) {
            Car car = new Sonata(distance);

            String actual = car.getName();

            assertThat(actual).isEqualTo("Sonata");
        }

        @DisplayName("소나타는 필요한 연료를 거리와 연비를 통해 계산하여 반환한다.")
        @ParameterizedTest(name = "[{index}] 거리: {0}, 필요한 연료: {1}")
        @CsvSource(value = {"100,10", "500,50", "3000,300"})
        void getFuelNeeded_sonata(int distance, int expected) {
            Car car = new Sonata(distance);

            int actual = car.getFuelNeeded();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @DisplayName("K5 구현체 테스트")
    @Nested
    class K5Test {
        @DisplayName("K5의 이름은 언제나 K5 이다.")
        @ParameterizedTest(name = "[{index}] 거리: {0}")
        @ValueSource(ints = {100, 500, 3000})
        void getName_k5(int distance) {
            Car car = new K5(distance);

            String actual = car.getName();

            assertThat(actual).isEqualTo("K5");
        }

        @DisplayName("K5는 필요한 연료를 거리와 연비를 통해 계산하여 반환한다.")
        @ParameterizedTest(name = "[{index}] 거리: {0}, 필요한 연료: {1}")
        @CsvSource(value = {"130,10", "260,20", "1300,100"})
        void getFuelNeeded_k5(int distance, int expected) {
            Car car = new K5(distance);

            int actual = car.getFuelNeeded();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @DisplayName("Avante 구현체 테스트")
    @Nested
    class AvanteTest {
        @DisplayName("아반떼의 이름은 언제나 Avante 이다.")
        @ParameterizedTest(name = "[{index}] 거리: {0}")
        @ValueSource(ints = {100, 500, 3000})
        void getName_avante(int distance) {
            Car car = new Avante(distance);

            String actual = car.getName();

            assertThat(actual).isEqualTo("Avante");
        }

        @DisplayName("아반떼는 필요한 연료를 거리와 연비를 통해 계산하여 반환한다.")
        @ParameterizedTest(name = "[{index}] 거리: {0}, 필요한 연료: {1}")
        @CsvSource(value = {"150,10", "300,20", "1500,100"})
        void getFuelNeeded_avante(int distance, int expected) {
            Car car = new Avante(distance);

            int actual = car.getFuelNeeded();

            assertThat(actual).isEqualTo(expected);
        }
    }

}
