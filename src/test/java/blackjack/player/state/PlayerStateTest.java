package blackjack.player.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerStateTest {

    @Test
    @DisplayName("플레이어가 블랙잭인 경우, BlackJack 상태로 전이한다.")
    void playerStateChangeOnBlackJackTest() {
        // when
        PlayerState state = PlayerState.createPlayerStateWithCardsOf(
                new Card(Shape.SPADE, Number.ACE),
                new Card(Shape.SPADE, Number.KING)
        );
        // then
        assertThat(state).isInstanceOf(BlackJackState.class);
    }

    @Test
    @DisplayName("딜러가 뽑은 처음 두 장의 합이 17점 이상인 경우, StandState 상태로 전이한다.")
    void dealerStateChangeTest() {
        // when
        PlayerState state = PlayerState.createDealerStateWithCardsOf(
                new Card(Shape.SPADE, Number.TEN),
                new Card(Shape.SPADE, Number.SEVEN)
        );
        // then
        assertThat(state).isInstanceOf(StandState.class);
    }
}
