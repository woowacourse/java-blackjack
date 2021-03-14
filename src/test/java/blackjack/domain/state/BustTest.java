package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BustTest {
    @Test
    @DisplayName("Hit에서 Bust 셍성")
    void create1() {
        Hand hand = new Hand(Arrays.asList(Card.from("2하트"), Card.from("10하트")));
        State state = StateFactory.draw(hand);
        state = state.draw(Card.from("K하트"));
        assertThat(state).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("Blackjack에서 Bust 셍성")
    void create2() {
        Hand hand = new Hand(Arrays.asList(Card.from("A하트"), Card.from("10하트")));
        State state = StateFactory.draw(hand);
        state = state.draw(Card.from("2하트"));
        assertThat(state).isInstanceOf(Bust.class);
    }
}
