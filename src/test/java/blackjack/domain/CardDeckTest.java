package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.component.CardFigure;
import blackjack.domain.card.component.CardNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardDeckTest {
    @DisplayName("52장의 카드 한 벌을 생성하는지 확인")
    @Test
    void cardDeckSizeTest() {
        int expected = 52;
        assertThat(new CardDeck().getSize()).isEqualTo(expected);
    }

    @DisplayName("의도한 카드를 주는 지 확인")
    @Test
    void getCardTest() {
        CardNumber cardNumber = CardNumber.ACE;
        CardFigure cardFigure = CardFigure.CLOVER;
        Card expectd = new Card(cardNumber, cardFigure);

        CardDeck cardDeck = new CardDeck();
        assertThat(cardDeck.getCard(cardNumber, cardFigure)).isEqualTo(expectd);
    }
}
