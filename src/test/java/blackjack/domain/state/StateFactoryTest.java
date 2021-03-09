package blackjack.domain.state;

import blackjack.domain.Hand;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class StateFactoryTest {

    @DisplayName("상태 반환 테스트")
    @Test
    void getState() {
        Hand blackjackHand = new Hand(Arrays.asList(
                Card.valueOf(Shape.CLOVER, CardValue.ACE),
                Card.valueOf(Shape.CLOVER, CardValue.JACK)));
        assertThat(StateFactory.getInstance(blackjackHand))
                .isInstanceOf(BlackJackState.class);
    }
}