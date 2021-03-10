package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HitTest {

    @Test
    @DisplayName("카드를 한 장 더 뽑았을 때 합이 21 보다 작다면 Hit 상태")
    void againDrawHit() {
        Hit hit = new Hit(Arrays.asList(
                new Card(Shape.HEART, Denomination.FOUR),
                new Card(Shape.HEART, Denomination.EIGHT)
        ));
        State state = hit.draw(new Card(Shape.SPADE, Denomination.FIVE));
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("카드를 한 장 더 뽑았을 때 합이 21을 넘는 경우 Bust 상태")
    void againDrawBust() {
        Hit hit = new Hit(Arrays.asList(
                new Card(Shape.HEART, Denomination.KING),
                new Card(Shape.HEART, Denomination.QUEEN)
        ));
        State state = hit.draw(new Card(Shape.SPADE, Denomination.FIVE));
        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("카드를 한 장 더 뽑았을 때 합이 21이 되는 경우")
    void againDrawStay() {
        Hit hit = new Hit(Arrays.asList(
                new Card(Shape.HEART, Denomination.KING),
                new Card(Shape.HEART, Denomination.QUEEN)
        ));
        State state = hit.draw(new Card(Shape.SPADE, Denomination.ACE));
        assertThat(state).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("사용자가 카드를 더 받지 않는 경우")
    void requestStopDraw() {
        Hit hit = new Hit(Arrays.asList(
                new Card(Shape.HEART, Denomination.KING),
                new Card(Shape.HEART, Denomination.QUEEN)
        ));
        State state = hit.stay();
        assertThat(state).isInstanceOf(Stay.class);
    }

    @Test
    @DisplayName("Hit 상태인 경우 키드를 더 뽑을 수 있다.")
    void isFinished() {
        State state = new Hit(Arrays.asList(
                new Card(Shape.HEART, Denomination.KING),
                new Card(Shape.HEART, Denomination.QUEEN)
        ));
        assertFalse(state.isFinished());
    }
}