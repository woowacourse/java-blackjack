package blackjack.domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayingCardTest {
    @Test
    void equals() {
        final PlayingCard card1 = PlayingCard.of(Suit.CLUBS, Denomination.ACE);
        final PlayingCard card2 = PlayingCard.of(Suit.CLUBS, Denomination.ACE);
        assertThat(card1).isEqualTo(card2);
    }
}
