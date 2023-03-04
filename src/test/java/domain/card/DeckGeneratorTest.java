package domain.card;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckGeneratorTest {

    @Test
    @DisplayName("생성한 덱은 52장이다.")
    void generateDeck() {
        DeckGenerator deckGenerator = new DeckGenerator();
        Deck deck = deckGenerator.generate();
        List<Card> cards = deck.getCards();

        Assertions.assertThat(cards.size()).isEqualTo(52);
    }
}
