package fuel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SonataTest {

    @DisplayName("주행 거리에 null 을 입력할 경우")
    @Test
    void distance_null() {
        assertThatThrownBy(() -> new Sonata(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("양수를 입력해주세요.");
    }
}