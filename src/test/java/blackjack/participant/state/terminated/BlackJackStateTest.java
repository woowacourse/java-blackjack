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

class BlackJackStateTest {

    private BlackJackState createBlackJackState() {
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.ACE),
                new Card(Shape.SPADE, Number.TEN)
        );
        return new BlackJackState(new Hand(cards));
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭인 경우 무승부로 판정한다.")
    void drawOnPlayerBlackJack() {
        // given
        BlackJackState state = createBlackJackState();
        BlackJackState dealerState = createBlackJackState();
        // when
        MatchResult matchResult = state.createMatchResult(dealerState);
        // then
        assertThat(matchResult).isEqualTo(MatchResult.TIE);
    }

    @Test
    @DisplayName("플레이어만 블랙잭인 경우 블랙잭 승리로 판정한다.")
    void winOnPlayerBlackJack() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.KING),
                new Card(Shape.SPADE, Number.TEN)
        );
        BlackJackState state = createBlackJackState();
        BlackJackState dealerState = new BlackJackState(new Hand(cards));
        // when
        MatchResult matchResult = state.createMatchResult(dealerState);
        // then
        assertThat(matchResult).isEqualTo(MatchResult.BLACKJACK_WIN);
    }
}
