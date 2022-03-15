package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = Deck.create();
    }

    @Test
    @DisplayName("카드 뽑는 기능 확인")
    public void checkPickCard() {
        PlayingCardFixMachine playingCardFixMachine = new PlayingCardFixMachine();
        PlayingCard playingCard = new PlayingCard(Suit.SPADE, Denomination.ACE);

        assertThat(deck.assignCard(playingCardFixMachine)).isEqualTo(playingCard);
    }
}
