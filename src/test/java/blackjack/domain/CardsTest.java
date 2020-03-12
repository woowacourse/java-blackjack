package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.CardFigure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CardsTest {
    @DisplayName("카드 합 계산 확인")
    @Test
    void sumTest() {
        CardDeck cardDeck = new CardDeck();
        Cards cards = new Cards();
        cards.add(cardDeck.of(CardNumber.THREE, CardFigure.CLOVER));
        cards.add(cardDeck.of(CardNumber.KING, CardFigure.CLOVER));

        int expected = 13;
        assertThat(cards.computeSum()).isEqualTo(expected);
    }

    @DisplayName("Ace가 있을 때의 카드 합 계산 확인(21이 초과하는 경우)")
    @Test
    void aceSumTest() {
        CardDeck cardDeck = new CardDeck();
        Cards cards = new Cards();
        cards.add(cardDeck.of(CardNumber.ACE, CardFigure.CLOVER));
        cards.add(cardDeck.of(CardNumber.KING, CardFigure.CLOVER));
        cards.add(cardDeck.of(CardNumber.QUEEN, CardFigure.HEART));

        int expected = 21;
        assertThat(cards.computeSum()).isEqualTo(expected);
    }

    @DisplayName("Ace가 있을 때의 카드 합 계산 확인(21이 초과하지 않는 경우)")
    @Test
    void aceSumTest2() {
        CardDeck cardDeck = new CardDeck();
        Cards cards = new Cards();
        cards.add(cardDeck.of(CardNumber.ACE, CardFigure.CLOVER));
        cards.add(cardDeck.of(CardNumber.KING, CardFigure.CLOVER));

        int expected = 21;
        assertThat(cards.computeSum()).isEqualTo(expected);
    }
}
