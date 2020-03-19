package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerOutcomeTest {

    @DisplayName("정적 팩터리")
    @Test
    void calculateWinPlayerMoreThanDealer() {
        CardsResult bust = new CardsResult(22);
        CardsResult twentyOne = new CardsResult(21);
        CardsResult twenty = new CardsResult(20);
        CardsResult nineteen = new CardsResult(19);
        CardsResult blackJack = new CardsResult(11, true, 2);

        assertThat(PlayerOutcome.of(twenty, nineteen)).isEqualTo(PlayerOutcome.WIN);
        assertThat(PlayerOutcome.of(bust, twentyOne)).isEqualTo(PlayerOutcome.LOSE);

        assertThat(PlayerOutcome.of(twenty, twenty)).isEqualTo(PlayerOutcome.DRAW);
        assertThat(PlayerOutcome.of(bust, bust)).isEqualTo(PlayerOutcome.LOSE);

        assertThat(PlayerOutcome.of(twenty, twentyOne)).isEqualTo(PlayerOutcome.LOSE);
        assertThat(PlayerOutcome.of(twenty, bust)).isEqualTo(PlayerOutcome.WIN);

        assertThat(PlayerOutcome.of(blackJack, twentyOne)).isEqualTo(PlayerOutcome.BLACKJACK);
        assertThat(PlayerOutcome.of(blackJack, blackJack)).isEqualTo(PlayerOutcome.DRAW);
    }
}