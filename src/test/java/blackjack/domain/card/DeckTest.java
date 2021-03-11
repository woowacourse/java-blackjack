package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeckTest {
    @Test
    @DisplayName("카드 덱을 생성한다.")
    void createDeck() {
        Deck deck = new Deck();

        assertThat(deck).isInstanceOf(Deck.class);
    }

    @Test
    @DisplayName("카드에서 덱 2장을 뽑는다.")
    void popTwo() {
        Deck deck = new Deck();

        int cardCount = deck.popToInitialCards()
                .getCards().size();

        assertThat(cardCount).isEqualTo(2);
    }

    @Test
    @DisplayName("카드에서 덱 1장을 뽑는다.")
    void popOne() {
        Deck deck = new Deck();

        Card card = deck.popOne();

        assertThat(card).isInstanceOf(Card.class);
    }
}
