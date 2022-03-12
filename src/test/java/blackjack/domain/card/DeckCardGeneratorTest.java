package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeckCardGeneratorTest {

    @Test
    @DisplayName("DeckCard는 52장이다.")
    void checkDeckCardSize() {
        DeckCardGenerator deckCardGenerator = new DeckCardGenerator();
        Stack<Card> bunchOfCards = deckCardGenerator.generate();

        assertThat(bunchOfCards.size()).isEqualTo(52);
    }
}
