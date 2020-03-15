package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.card.UserCards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutcomeTest {

    @DisplayName("정적 팩터리")
    @Test
    void calculateWinPlayerMoreThanDealer() {
        Score bust = new Score(22, 2);
        Score twentyOne = new Score(21, 3);
        Score twenty = new Score(20, 2);
        Score nineteen = new Score(19, 2);

        assertThat(Outcome.from(twenty, nineteen)).isEqualTo(Outcome.PLAYER_WIN);
        assertThat(Outcome.from(bust, twentyOne)).isEqualTo(Outcome.PLAYER_LOSE);

        assertThat(Outcome.from(twenty, twenty)).isEqualTo(Outcome.PLAYER_DRAW);
        assertThat(Outcome.from(bust, bust)).isEqualTo(Outcome.PLAYER_LOSE);

        assertThat(Outcome.from(twenty, twentyOne)).isEqualTo(Outcome.PLAYER_LOSE);
        assertThat(Outcome.from(twenty, bust)).isEqualTo(Outcome.PLAYER_WIN);
    }
}