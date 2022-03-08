package fuelinjection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CarTest {

    private static Stream<Arguments> provideSource() {
        return Stream.of(
                Arguments.of(new Sonata(150)),
                Arguments.of(new Avante(225)),
                Arguments.of(new K5(195))
        );
    }

    @ParameterizedTest
    @MethodSource("provideSource")
    @DisplayName("연료 생성 값 확인")
    void getChargeQuantity(Car car) {
        // when
        double actual = car.getChargeQuantity();

        // then
        assertThat(actual).isCloseTo(15, Percentage.withPercentage(0.99));
    }
}
