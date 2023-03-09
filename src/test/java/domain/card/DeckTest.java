package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("덱의 맨 위에 있는 카드를 뽑아서 반환한다.")
    void getCardTopOfDeck() {
        DeckGenerator deckGenerator = new DeckGenerator();
        Deck deck = deckGenerator.generate();
        int topOfDeckCardIndex = deck.cards.size() - 1;
        Card topOfDeckCard = deck.cards.get(topOfDeckCardIndex);

        Assertions.assertThat(deck.drawCard()).isEqualTo(topOfDeckCard);
    }
}
