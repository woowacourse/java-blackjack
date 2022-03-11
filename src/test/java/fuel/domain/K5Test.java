package fuel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class K5Test {

    @DisplayName("주행 거리에 음수를 입력할 경우")
    @Test
    void distance_negative() {
        assertThatThrownBy(() -> new K5(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수는 입력할 수 없습니다.");
    }
}
