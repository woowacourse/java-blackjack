package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitOrStayAnswerTest {

    @Test
    @DisplayName("isHit의 파라미터로 y이면 참이 반환된다.")
    void is_hit_true() {
        assertThat(HitOrStayAnswer.isHit("y")).isTrue();
    }

    @Test
    @DisplayName("isHit의 파라미터로 n이면 거짓이 반환된다.")
    void is_hit_false() {
        assertThat(HitOrStayAnswer.isHit("n")).isFalse();
    }

    @Test
    @DisplayName("containsValue로 enum의 값이 있는지 확인한다.")
    void contains_value() {
        assertThat(HitOrStayAnswer.containsValue("a")).isFalse();
        assertThat(HitOrStayAnswer.containsValue("y")).isTrue();
        assertThat(HitOrStayAnswer.containsValue("n")).isTrue();
    }
}
