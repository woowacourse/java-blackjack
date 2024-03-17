package blackjack.model.deck;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardDeckTest {
    @DisplayName("카드 한장을 발급한다")
    @Test
    void drawCard() {
        Card card = CardDeck.drawCard();
        assertThat(card.getCardNumber()).isInstanceOf(CardNumber.class);
        assertThat(card.getCardShape()).isInstanceOf(CardShape.class);
    }

    @DisplayName("카드 여러장을 발급한다")
    @Test
    void drawCards() {
        List<Card> cards = CardDeck.drawCards();

        assertThat(cards).hasSize(2);
    }
}
