package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerTest {

    @DisplayName("'y' 입력 시 boolean을, 'n' 입력 시 false를 반환한다.")
    @Test
    void generate_test() {
        assertThat(Answer.isYes("y")).isTrue();
        assertThat(Answer.isYes("n")).isFalse();
    }
}