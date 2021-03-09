package rentcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TripDistanceTest {
    @Test
    @DisplayName("음의 값 입력시 에러 발생")
    void validateNonNegative() {
        assertThatThrownBy(() -> new TripDistance(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}