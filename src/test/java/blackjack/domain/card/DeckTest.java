package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    private List<PlayingCard> deck;

    @BeforeEach
    public void setUp() {
        deck = Deck.getPlayingCards();
    }

    @Test
    @DisplayName("Deck 반환 확인")
    public void checkCardReturn() {
        assertThat(deck.size()).isEqualTo(52);
    }
}
