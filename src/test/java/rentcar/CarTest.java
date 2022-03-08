package rentcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CarTest {

    @ParameterizedTest(name = "{0} : 주행 거리, {1} : 연료량")
    @CsvSource(value = {"10,1", "100,10", "200,20"})
    @DisplayName("소나타가 주입해야 할 연료량을 계산한다.")
    void calculateSonataChargeQuantity(int tripDistance, int chargeQuantity) {
        Car car = new Sonata(tripDistance);
        double expected = car.getChargeQuantity();

        assertThat(expected).isEqualTo(chargeQuantity);
    }

    @ParameterizedTest(name = "{0} : 주행 거리, {1} : 연료량")
    @CsvSource(value = {"15,1", "150,10", "300,20"})
    @DisplayName("아반테가 주입해야 할 연료량을 계산한다.")
    void calculateAvanteChargeQuantity(int tripDistance, int chargeQuantity) {
        Car car = new Avante(tripDistance);
        double expected = car.getChargeQuantity();

        assertThat(expected).isEqualTo(chargeQuantity);
    }

    @ParameterizedTest(name = "{0} : 주행 거리, {1} : 연료량")
    @CsvSource(value = {"13,1", "130,10", "260,20"})
    @DisplayName("아반테가 주입해야 할 연료량을 계산한다.")
    void calculateK5ChargeQuantity(int tripDistance, int chargeQuantity) {
        Car car = new K5(tripDistance);
        double expected = car.getChargeQuantity();

        assertThat(expected).isEqualTo(chargeQuantity);
    }

    @ParameterizedTest(name = "{0} : 주행 거리")
    @ValueSource(ints = {0, -1})
    @DisplayName("주행거리가 0이하인 경우 예외를 발생한다.")
    void throwExceptionNegativeTripDistance(int tripDistance) {

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Sonata(tripDistance))
                .withMessageContaining("주행 거리가 0 이하일 수 없습니다.");
    }
}
