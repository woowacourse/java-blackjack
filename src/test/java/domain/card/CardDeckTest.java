package domain.card;

import factory.CardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardDeckTest {
    @Test
    @DisplayName("CardDeck 생성")
    void create() {
        assertThat(new CardDeck(CardFactory.create())).isInstanceOf(CardDeck.class);
    }

    @Test
    @DisplayName("CardDeck 카드 추출")
    void drawOne() {
        CardDeck cardDeck = new CardDeck(CardFactory.create());
        assertThat(cardDeck.drawOne()).isInstanceOf(Card.class);
    }
}
