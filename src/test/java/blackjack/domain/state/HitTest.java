package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.CardValue;
import blackjack.domain.cards.Hand;
import blackjack.domain.cards.Shape;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stay;
import blackjack.domain.state.hitstrategy.DealerStrategy;
import blackjack.domain.state.hitstrategy.PlayerStrategy;
import blackjack.domain.state.running.Hit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitTest {

    @Test
    @DisplayName("손패가 버스트가 되었을 때 Bust 상태로 이동하는지 검증")
    void moveToBust() {
        State state = new Hit(new Hand(), new PlayerStrategy());
        state = state.draw(Card.valueOf(Shape.SPADE, CardValue.TEN));
        state = state.draw(Card.valueOf(Shape.HEART, CardValue.TEN));
        assertThat(state).isInstanceOf(Hit.class);
        state = state.draw(Card.valueOf(Shape.DIAMOND, CardValue.TEN));
        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("손패가 3장이고 합이 21일 때 블랙잭으로 취급 안하는지 확인")
    void moveNotToBlackJack() {
        State state = new Hit(new Hand(), new PlayerStrategy());
        state = state.draw(Card.valueOf(Shape.SPADE, CardValue.TEN));
        state = state.draw(Card.valueOf(Shape.HEART, CardValue.SIX));
        assertThat(state).isInstanceOf(Hit.class);
        state = state.draw(Card.valueOf(Shape.DIAMOND, CardValue.FIVE));
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("손패가 2장이고 합이 21일 때 블랙잭으로 취급하는지 확인")
    void moveToBlackJack() {
        State state = new Hit(new Hand(), new PlayerStrategy());
        state = state.draw(Card.valueOf(Shape.SPADE, CardValue.TEN));
        state = state.draw(Card.valueOf(Shape.HEART, CardValue.ACE));
        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("딜러의 손패가 16초과일 때 Stay 상태가 되는지 확인")
    void dealerMovesToStay() {
        State state = new Hit(new Hand(), new DealerStrategy());
        state = state.draw(Card.valueOf(Shape.SPADE, CardValue.TEN));
        state = state.draw(Card.valueOf(Shape.HEART, CardValue.SIX));
        assertThat(state).isInstanceOf(Hit.class);

        state = new Hit(new Hand(), new DealerStrategy());
        state = state.draw(Card.valueOf(Shape.SPADE, CardValue.TEN));
        state = state.draw(Card.valueOf(Shape.HEART, CardValue.SEVEN));
        assertThat(state).isInstanceOf(Stay.class);
    }
}
