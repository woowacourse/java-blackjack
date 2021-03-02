package rentcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarTest {
    private static Stream<Arguments> provideCarInstance() {
        return Stream.of(
                Arguments.of(new Sonata(150)),
                Arguments.of(new K5(100)),
                Arguments.of(new Avante(120))
        );
    }

    @ParameterizedTest
    @DisplayName("올바른 차량 상속 확인")
    @MethodSource("provideCarInstance")
    void createCar(Object obj) {
        assertTrue(obj instanceof Car);
    }

    @Test
    @DisplayName("주입해야할 연료량 구하기")
    void chargeQuantity() {
        int expectedValue = 15;
        Sonata sonata = new Sonata(150);
        assertThat(sonata.getChargeQuantity()).isEqualTo(expectedValue);
    }
}
