package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class HitTest {
    @Test
    @DisplayName("최초 생성")
    void create() {
        Hand hand = new Hand(Arrays.asList(Card.from("2하트"), Card.from("10하트")));
        State state = StateFactory.draw(hand);
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("생성후 카드 추가시 Hit")
    void draw1() {
        Hand hand = new Hand(Arrays.asList(Card.from("2하트"), Card.from("10하트")));
        State state = StateFactory.draw(hand);
        state = state.draw(Card.from("3하트"));
        assertThat(state).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("생성후 카드 추가시 21인 경우 Hit")
    void draw2() {
        Hand hand = new Hand(Arrays.asList(Card.from("2하트"), Card.from("10하트")));
        State state = StateFactory.draw(hand);
        state = state.draw(Card.from("9하트"));
        assertThat(state).isInstanceOf(Hit.class);
    }
}
