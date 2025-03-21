package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.game.Deck;
import blackjack.model.game.DeckInitializer;
import org.junit.jupiter.api.Test;

class DeckInitializerTest {

    @Test
    void 모든_카드가_존재하는_덱을_생성한다() {
        // given
        DeckInitializer deckInitializer = new DeckInitializer();

        // when
        Deck deck = deckInitializer.generateDeck();
        // then
        assertThat(deck.getCardCount()).isEqualTo(52);
    }
}
