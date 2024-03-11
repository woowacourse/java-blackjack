package blackjack.model.deck;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.cards.Card;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckGeneratorTest {
    @DisplayName("모양과 숫자가 모두 다른 카드 한벌을 생성한다.")
    @Test
    void generateDeck() {
        DeckGenerator deckGenerator = new DeckGenerator();
        List<Card> deck = deckGenerator.generate();

        Set<Card> nonDuplicateDeck = new HashSet<>(deck);

        assertThat(nonDuplicateDeck).hasSize(52);
    }
}
