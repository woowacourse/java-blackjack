package domain.card;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("생성한 덱은 52장이다.")
    void generateDeck() {
        Deck deck = new Deck();
        List<Card> cards = deck.getCards();

        Assertions.assertThat(cards.size()).isEqualTo(52);
    }

    @Test
    @DisplayName("덱의 맨 위에 있는 카드를 뽑아서 반환한다.")
    void getCardTopOfDeck() {
        Deck deck = new Deck();
        int topOfDeckCardIndex = deck.cards.size() - 1;
        Card topOfDeckCard = deck.cards.get(topOfDeckCardIndex);

        Assertions.assertThat(deck.drawCard()).isEqualTo(topOfDeckCard);
    }
}
