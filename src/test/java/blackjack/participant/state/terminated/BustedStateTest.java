package blackjack.participant.state.terminated;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import blackjack.game.MatchResult;
import blackjack.participant.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustedStateTest {

    @Test
    @DisplayName("플레이어가 버스트라면 딜러의 상태와 상관없이 패배한다.")
    void loseOnBustTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.TEN),
                new Card(Shape.DIAMOND, Number.TEN),
                new Card(Shape.HEART, Number.TEN)
        );
        BustedState state = new BustedState(new Hand(cards));
        BustedState dealerState = new BustedState(new Hand(cards));
        // when
        MatchResult matchResult = state.createMatchResult(dealerState);
        // then
        assertThat(matchResult).isEqualTo(MatchResult.DEALER_WIN);
    }
}
