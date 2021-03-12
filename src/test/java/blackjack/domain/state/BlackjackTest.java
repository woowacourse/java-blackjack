package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackTest {
    @Test
    void create() {
        Hand hand = new Hand(Arrays.asList(Card.from("A하트"), Card.from("10하트")));
        State state = StateFactory.draw(hand);
        assertThat(state).isInstanceOf(Blackjack.class);
    }
}
