package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }

    @Test
    @DisplayName("덱 객체 생성 확인")
    public void createDeck() {
        assertThat(deck).isInstanceOf(Deck.class);
    }

    @Test
    @DisplayName("deck cards에 card 더하는 로직 확인")
    public void checkAddCard() {
        deck.addCard(new Card(Suit.SPADE, Rank.EIGHT));
        Deck comparedDeck = new Deck();
        comparedDeck.addCard(new Card(Suit.SPADE, Rank.EIGHT));
        assertThat(deck).isEqualTo(comparedDeck);
    }
}
