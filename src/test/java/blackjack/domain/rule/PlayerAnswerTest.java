package blackjack.domain.rule;

import blackjack.controller.dto.request.PlayerAnswer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerAnswerTest {

    @Test
    @DisplayName("y와 n을 PlayerAnswer 객체로 포장")
    void yesOrNo() {
        assertThat(PlayerAnswer.of("y")).isEqualTo(PlayerAnswer.Y);
        assertThat(PlayerAnswer.of("n")).isEqualTo(PlayerAnswer.N);
    }
}