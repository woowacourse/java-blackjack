package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import blackjack.domain.Response;
import blackjack.domain.cards.Card;
import blackjack.domain.cards.CardValue;
import blackjack.domain.cards.Hand;
import blackjack.domain.cards.Shape;
import blackjack.domain.state.hitstrategy.DealerStrategy;
import blackjack.domain.state.hitstrategy.PlayerStrategy;
import blackjack.domain.state.running.Hit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinishedTest {

    @Test
    @DisplayName("버스트 상태에서 카드를 뽑으려하면 예외처리")
    void drawWhenBust() {
        State state = new Hit(new Hand(), new DealerStrategy());
        state = state.draw(Card.valueOf(Shape.SPADE, CardValue.TEN));
        State finalState = state.draw(Card.valueOf(Shape.HEART, CardValue.TEN));
        assertThatIllegalStateException().isThrownBy(() -> finalState
            .draw(Card.valueOf(Shape.SPADE, CardValue.KING)))
            .withMessage("카드를 뽑을 수 없는 상태입니다.");
    }

    @Test
    @DisplayName("블랙잭 상태에서 카드를 뽑으려하면 예외처리")
    void drawWhenBlackJack() {
        State state = new Hit(new Hand(), new DealerStrategy());
        state = state.draw(Card.valueOf(Shape.SPADE, CardValue.TEN));
        State finalState = state.draw(Card.valueOf(Shape.HEART, CardValue.ACE));
        assertThatIllegalStateException().isThrownBy(() -> finalState
            .draw(Card.valueOf(Shape.SPADE, CardValue.KING)))
            .withMessage("카드를 뽑을 수 없는 상태입니다.");
    }

    @Test
    @DisplayName("스테이 상태에서 카드를 뽑으려하면 예외처리")
    void drawWhenStay() {
        State state = new Hit(new Hand(), new PlayerStrategy());
        state = state.draw(Card.valueOf(Shape.SPADE, CardValue.TEN));
        state = state.draw(Card.valueOf(Shape.HEART, CardValue.FIVE));
        State finalState = state.moveStateByResponse(Response.NEGATIVE);
        assertThatIllegalStateException().isThrownBy(() -> finalState
            .draw(Card.valueOf(Shape.SPADE, CardValue.KING)))
            .withMessage("카드를 뽑을 수 없는 상태입니다.");
    }

    @Test
    @DisplayName("스테이 상태에서 플레이어의 응답을 받으면 예외처리")
    void moveStateByResponseWhenStay() {
        State state = new Hit(new Hand(), new PlayerStrategy());
        state = state.draw(Card.valueOf(Shape.SPADE, CardValue.TEN));
        State finalState = state.moveStateByResponse(Response.NEGATIVE);
        assertThatIllegalStateException().isThrownBy(() -> finalState
            .moveStateByResponse(Response.NEGATIVE))
            .withMessage("더 이상 진행할 수 없는 상태입니다.");
    }
}
