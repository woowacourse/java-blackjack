package blackjack.blackjack.generator;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.blackjack.card.Card;
import blackjack.blackjack.card.generator.DeckGenerator;
import blackjack.blackjack.card.generator.ShuffleDeckGenerator;
import java.util.Deque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ShuffleDeckGeneratorTest {

    private DeckGenerator deckGenerator;

    @BeforeEach
    void setUp() {
        deckGenerator = new ShuffleDeckGenerator();
    }

    @Test
    @DisplayName("셔플된 덱을 만든다")
    void makeShuffleDeck() {
        // given

        // when
        final Deque<Card> deck = deckGenerator.makeDeck();

        // then
        assertThat(deck).hasSize(52);
    }
}
