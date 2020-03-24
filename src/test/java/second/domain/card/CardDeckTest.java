package second.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import second.domain.card.provider.CardDeck;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardDeckTest {
    @Test
    @DisplayName("카드덱 생성")
    void initialize() {
        CardDeck CardDeck = new CardDeck();

        assertThat(CardDeck).isInstanceOf(CardDeck.class);
    }

    @Test
    void drawCard() {
        CardDeck CardDeck = new CardDeck();

        assertThat(CardDeck.pickCard()).isInstanceOf(Card.class);
    }

    @Test
    @DisplayName("카드 수를 넘어가는 뽑기")
    void drawCardWhenLimit() {
        CardDeck CardDeck = new CardDeck();
        for (int i = 0; i < 48; i++) {
            CardDeck.pickCard();
        }

        assertThatThrownBy(CardDeck::pickCard).isInstanceOf(IllegalStateException.class);
    }
}