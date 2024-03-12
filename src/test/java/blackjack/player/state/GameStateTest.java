package blackjack.player.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import blackjack.player.state.playing.DealerState;
import blackjack.player.state.playing.PlayerState;
import blackjack.player.state.terminated.BlackJackState;
import blackjack.player.state.terminated.StandState;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStateTest {

    @Test
    @DisplayName("뽑은 두 장이 블랙잭인 경우, BlackJack 상태로 전이한다.")
    void playerStateChangeOnBlackJackTest() {
        // when
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.ACE),
                new Card(Shape.SPADE, Number.KING)
        );
        GameState state = new PlayerState();
        for (final Card card : cards) {
            state = state.drawCard(card);
        }
        // then
        assertThat(state).isInstanceOf(BlackJackState.class);
    }

    @Test
    @DisplayName("딜러가 뽑은 처음 두 장의 합이 17점 이상인 경우, StandState 상태로 전이한다.")
    void dealerStateChangeTest() {
        // when
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.TEN),
                new Card(Shape.SPADE, Number.SEVEN)
        );
        GameState state = new DealerState();
        for (final Card card : cards) {
            state = state.drawCard(card);
        }
        // then
        assertThat(state).isInstanceOf(StandState.class);
    }
}
