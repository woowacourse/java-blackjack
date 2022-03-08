package rentcompany;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CarTest {

    @Test
    @DisplayName("소나타 정상 생성을 확인한다.")
    void createSonata() {
        int tripDistance = 100;
        assertThatCode(() -> new Sonata(tripDistance))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("아반떼 정상 생성을 확인한다.")
    void createAvante() {
        int tripDistance = 100;
        assertThatCode(() -> new Avante(tripDistance))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("K5 정상 생성을 확인한다.")
    void createK5() {
        int tripDistance = 100;
        assertThatCode(() -> new K5(tripDistance))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "{2} 자동차의 주입할 연료량은 {1}입니다.")
    @MethodSource("provideCarAndFuelAmount")
    @DisplayName("차량별 이동거리 기준 주입해야할 연료량을 확인한다.")
    void calculateChargeFuelAmount(Car car, double expectedChargeFuelAmount, String carName) {
        final double actual = car.getChargeQuantity();

        assertThat(actual).isEqualTo(expectedChargeFuelAmount);
    }

    private static Stream<Arguments> provideCarAndFuelAmount() {
        return Stream.of(
                Arguments.of(new Sonata(100), 10, "SONATA"),
                Arguments.of(new Avante(150), 10, "AVANTE"),
                Arguments.of(new K5(130), 10, "K5")
        );
    }
}
