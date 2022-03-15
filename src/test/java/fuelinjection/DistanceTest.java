package fuelinjection;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DistanceTest {
    @ParameterizedTest(name = "거리가 {0}이면 예외가 발생한다.")
    @ValueSource(ints = {-100, 0})
    void construct_exception_negative(int distance) {
        assertThatThrownBy(() -> new Distance(distance))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 거리는 0이하 일 수 없습니다.");
    }
}
