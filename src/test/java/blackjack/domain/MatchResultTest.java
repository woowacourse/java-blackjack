package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchResultTest {

    @Test
    @DisplayName("승패 확인")
    void checkPlayerMatchResult() {
        assertThat(MatchResult.matchPlayerAndDealer(10, 8)).isEqualTo(MatchResult.WIN);
        assertThat(MatchResult.matchPlayerAndDealer(22, 8)).isEqualTo(MatchResult.LOSE);
        assertThat(MatchResult.matchPlayerAndDealer(10, 10)).isEqualTo(MatchResult.DRAW);
    }
}
