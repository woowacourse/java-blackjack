package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberTest {
    @Test
    @DisplayName("A 판별 테스트")
    void isATest() {
        Number number = Number.ACE;
        assertThat(number.isA()).isTrue();
    }

}
