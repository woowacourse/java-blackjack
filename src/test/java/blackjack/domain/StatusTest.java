package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusTest {

    @Test
    @DisplayName("점수를 받아 상태 반환")
    void evaluateScore() {
        Assertions.assertThat(Status.evaluateScore(20)).isEqualTo(Status.HIT);
        Assertions.assertThat(Status.evaluateScore(21)).isEqualTo(Status.BLACKJACK);
        Assertions.assertThat(Status.evaluateScore(22)).isEqualTo(Status.BURST);
    }
}