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
        IndexGenerator indexGenerator = maxRange -> 0;
        CardDeck cardDeck = new CardDeck(indexGenerator);

        Card card = cardDeck.drawCard();
        assertThat(card.getCardNumber()).isEqualTo(CardNumber.ACE);
        assertThat(card.getCardShape()).isEqualTo(CardShape.SPADE);
    }

    @DisplayName("카드 여러장을 발급한다")
    @Test
    void drawCards() {
        IndexGenerator indexGenerator = maxRange -> 1;
        CardDeck cardDeck = new CardDeck(indexGenerator);

        List<Card> cards = cardDeck.drawCards();

        assertThat(cards).hasSize(2);
    }
}
