package domain.state;

import domain.card.Card;
import domain.member.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HitTest {

    @DisplayName("Hit 상태에서 카드를 뽑아 21 이하이면 다시 Hit 상태를 반환한다")
    @Test
    void draw_Under21_ReturnHit() {
        State state = new Hit(new Hand());

        State nextState = state.draw(Card.from("2", "스페이드"));

        assertThat(nextState).isInstanceOf(Hit.class);
    }

    @DisplayName("Hit 상태에서 카드를 뽑아 21을 초과하면 Bust 상태를 반환한다")
    @Test
    void draw_Over21_ReturnBust() {
        Hand hand = new Hand();
        hand.appendCard(Card.from("10", "스페이드"));
        hand.appendCard(Card.from("10", "하트"));
        State state = new Hit(hand);

        State nextState = state.draw(Card.from("2", "클로버"));

        assertThat(nextState).isInstanceOf(Bust.class);
    }

    @DisplayName("Hit 상태에서 stay를 호출하면 Stay 상태를 반환한다")
    @Test
    void stay_ReturnStay() {
        State state = new Hit(new Hand());
        State nextState = state.stay();

        assertThat(nextState).isInstanceOf(Stay.class);
    }
}
