package blackjack.domain.state;

import blackjack.domain.Hand;
import blackjack.domain.TestSetUp;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import blackjack.exception.InvalidNameInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class StateFactoryTest {

    @DisplayName("블랙잭, HIT 상태 반환 테스트")
    @Test
    void getState() throws InvalidNameInputException {
        Hand blackjackHand = TestSetUp.createBustPlayer().getHand();
        assertThat(StateFactory.getInstance(blackjackHand))
                .isInstanceOf(BlackJackState.class);

        Hand hitHand = TestSetUp.createTiePlayer().getHand();
        assertThat(StateFactory.getInstance(hitHand))
                .isInstanceOf(Hit.class);
    }
}