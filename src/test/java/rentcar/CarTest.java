package rentcar;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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
}
