package domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import config.DeckFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    @DisplayName("카드 개수 테스트")
    void cardSizeTest() {
        DeckFactory deckFactory = new DeckFactory();

        assertThat(deckFactory.create()).isInstanceOf(Deck.class);
    }

    @Test
    @DisplayName("카드 한 장 뽑기 테스트")
    void hitCardTest() {
        DeckFactory deckFactory = new DeckFactory();
        Deck cardDeck = deckFactory.create();

        assertThat(cardDeck.hitCard()).isInstanceOf(Card.class);
    }

}
