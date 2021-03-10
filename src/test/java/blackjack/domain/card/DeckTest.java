package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {
    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }

    @DisplayName("Deck 객체를 생성한다.")
    @Test
    public void createDeck() {
        assertThat(deck).isInstanceOf(Deck.class);
    }

    @DisplayName("덱에서 카드 두 장을 꺼낸다.")
    @Test
    public void popTwo() {
        Cards cards = deck.popTwo();

        assertThat(cards.getCards()).hasSize(2);
    }

    @DisplayName("덱에서 카드 한 장을 꺼낸다.")
    @Test
    public void popOne() {
        Cards cards = deck.popOne();

        assertThat(cards.getCards()).hasSize(1);
    }
}
