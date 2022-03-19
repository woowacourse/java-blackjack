package blackjack_statepattern.state;

import static blackjack_statepattern.Fixture.SPADES_ACE;
import static blackjack_statepattern.Fixture.SPADES_JACK;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack_statepattern.Card;
import blackjack_statepattern.Cards;
import blackjack_statepattern.Denomination;
import blackjack_statepattern.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackTest {

    @Test
    @DisplayName("블랙잭인 상태에서 카드를 받지 못한다.")
    void blackjackDraw() {
        Blackjack blackjack = new Blackjack(new Cards(SPADES_ACE, SPADES_JACK));
        assertThatThrownBy(
                () -> blackjack.draw(Card.of(Suit.SPADES, Denomination.TEN))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
