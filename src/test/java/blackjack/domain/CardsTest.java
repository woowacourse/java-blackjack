package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.component.CardFigure;
import blackjack.domain.card.component.CardNumber;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardsTest {

    @DisplayName("카드 합 계산 확인")
    @Test
    void sumTest() {
        Cards cards = new Cards();
        cards.add(new Card(CardNumber.THREE, CardFigure.CLOVER));
        cards.add(new Card(CardNumber.KING, CardFigure.CLOVER));

        int expected = 13;
        int actual = cards.computePoint();
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("카드 합 계산 확인 - ACE가 1이여야 할 때")
    @Test
    void sumTest_AceIs1() {
        Cards cards = new Cards();
        cards.add(new Card(CardNumber.ACE, CardFigure.CLOVER));
        cards.add(new Card(CardNumber.KING, CardFigure.CLOVER));
        cards.add(new Card(CardNumber.QUEEN, CardFigure.HEART));

        int expected = 21;
        int actual = cards.computePoint();
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("카드 합 계산 확인 - ACE가 11이여야 할 때")
    @Test
    void sumTest_AceIs11() {
        Cards cards = new Cards();
        cards.add(new Card(CardNumber.ACE, CardFigure.CLOVER));
        cards.add(new Card(CardNumber.KING, CardFigure.CLOVER));

        int expected = 21;
        int actual = cards.computePoint();
        AssertionsForClassTypes.assertThat(actual).isEqualTo(expected);
    }
}
