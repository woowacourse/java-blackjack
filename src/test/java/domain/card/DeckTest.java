package domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import config.CardDeckFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    @DisplayName("카드 개수 테스트")
    void cardSizeTest() {
        CardDeckFactory cardDeckFactory = new CardDeckFactory();

        assertThat(cardDeckFactory.create()).isInstanceOf(Deck.class);
    }

    @Test
    @DisplayName("카드 한 장 뽑기 테스트")
    void hitCardTest() {
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        Deck cardDeck = cardDeckFactory.create();

        assertThat(cardDeck.hitCard()).isInstanceOf(Card.class);
    }

}
