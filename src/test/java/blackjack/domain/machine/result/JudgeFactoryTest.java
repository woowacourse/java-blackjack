package blackjack.domain.machine.result;

import static blackjack.domain.machine.result.Fixtures.HEART_ACE;
import static blackjack.domain.machine.result.Fixtures.SPADE_ACE;
import static blackjack.domain.machine.result.Fixtures.SPADE_EIGHT;
import static blackjack.domain.machine.result.Fixtures.SPADE_JACK;
import static blackjack.domain.machine.result.Fixtures.SPADE_NINE;
import static blackjack.domain.machine.result.Fixtures.SPADE_TEN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.machine.Score;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgeFactoryTest {

    @Test
    @DisplayName("블랙잭이 아닌 승리일 때 Win 반환")
    void judgeWin() {
        Score playerScore = new Score(Set.of(SPADE_TEN, SPADE_JACK));
        Score dealerScore = new Score(Set.of(SPADE_EIGHT, SPADE_ACE));
        assertThat(JudgeFactory.matchResult(playerScore, dealerScore)).isInstanceOf(Win.class);
    }

    @Test
    @DisplayName("블랙잭 승리일 때 Blackjack 반환")
    void judgeBlackjack() {
        Score playerScore = new Score(Set.of(SPADE_ACE, SPADE_JACK));
        Score dealerScore = new Score(Set.of(SPADE_EIGHT, SPADE_TEN));
        assertThat(JudgeFactory.matchResult(playerScore, dealerScore)).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("무승부 시 Draw 반환")
    void judgeDraw() {
        Score playerScore = new Score(Set.of(SPADE_ACE, SPADE_EIGHT));
        Score dealerScore = new Score(Set.of(SPADE_NINE, SPADE_TEN));
        assertThat(JudgeFactory.matchResult(playerScore, dealerScore)).isInstanceOf(Draw.class);
    }

    @Test
    @DisplayName("블랙잭 무승부 시 Draw 반환")
    void judgeBlackjackDraw() {
        Score playerScore = new Score(Set.of(SPADE_ACE, SPADE_JACK));
        Score dealerScore = new Score(Set.of(HEART_ACE, SPADE_TEN));
        assertThat(JudgeFactory.matchResult(playerScore, dealerScore)).isInstanceOf(Draw.class);
    }

    @Test
    @DisplayName("패배시 Lose 반환")
    void judgeLose() {
        Score playerScore = new Score(Set.of(SPADE_ACE, SPADE_NINE));
        Score dealerScore = new Score(Set.of(HEART_ACE, SPADE_TEN));
        assertThat(JudgeFactory.matchResult(playerScore, dealerScore)).isInstanceOf(Lose.class);
    }
}
