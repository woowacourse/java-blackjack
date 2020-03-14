package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.component.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.component.CardFigure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CardsTest {
    @DisplayName("카드 합 계산 확인")
    @Test
    void sumTest() {
        CardDeck cardDeck = new CardDeck();
        Cards cards = new Cards();
        cards.add(cardDeck.getCard(CardNumber.THREE, CardFigure.CLOVER));
        cards.add(cardDeck.getCard(CardNumber.KING, CardFigure.CLOVER));

        int expected = 13;
        assertThat(cards.computeSum()).isEqualTo(expected);
    }

    @DisplayName("카드 합 계산 확인 - ACE가 1이여야 할 때")
    @Test
    void sumTest_AceIs1() {
        CardDeck cardDeck = new CardDeck();
        Cards cards = new Cards();
        cards.add(cardDeck.getCard(CardNumber.ACE, CardFigure.CLOVER));
        cards.add(cardDeck.getCard(CardNumber.KING, CardFigure.CLOVER));
        cards.add(cardDeck.getCard(CardNumber.QUEEN, CardFigure.HEART));

        int expected = 21;
        assertThat(cards.computeSum()).isEqualTo(expected);
    }

    @DisplayName("카드 합 계산 확인 - ACE가 11이여야 할 때")
    @Test
    void sumTest_AceIs11() {
        CardDeck cardDeck = new CardDeck();
        Cards cards = new Cards();
        cards.add(cardDeck.getCard(CardNumber.ACE, CardFigure.CLOVER));
        cards.add(cardDeck.getCard(CardNumber.KING, CardFigure.CLOVER));

        int expected = 21;
        assertThat(cards.computeSum()).isEqualTo(expected);
    }
}
