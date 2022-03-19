package blackjack_statepattern;

import static blackjack_statepattern.Fixture.SPADES_ACE;
import static blackjack_statepattern.Fixture.SPADES_JACK;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackTest {

    @Test
    @DisplayName("블랙잭인 상태에서 카드를 받지 못한다.")
    void blackjackDraw() {
        final var state = Ready.start(SPADES_JACK, SPADES_ACE);
        assertThatThrownBy(
                () -> state.draw(Card.of(Suit.SPADES, Denomination.TEN))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
