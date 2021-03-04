package blackjacktest.domaintest;

import blackjack.domain.MatchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchResultTest {

    @Test
    @DisplayName("승패 확인")
    void checkPlayerMatchResult() {
        assertThat(MatchResult.getPlayerMatchResult(10, 8)).isEqualTo(MatchResult.WIN);
        assertThat(MatchResult.getPlayerMatchResult(22, 8)).isEqualTo(MatchResult.LOSE);
        assertThat(MatchResult.getPlayerMatchResult(10, 10)).isEqualTo(MatchResult.DRAW);
    }
}
