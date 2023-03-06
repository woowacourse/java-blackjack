package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StakeTest {

    @Test
    @DisplayName("0미만일 수  없다")
    void minimumTest() {
        assertThatThrownBy(() -> new Stake(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("미만");
    }

    @Test
    @DisplayName("100_000 초과일 수 없다")
    void maximumTest() {
        assertThatThrownBy(() -> new Stake(100_001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("초과");
    }
}
