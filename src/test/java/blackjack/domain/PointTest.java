package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.component.CardFigure;
import blackjack.domain.card.component.CardNumber;
import blackjack.domain.user.component.Point;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PointTest {
    @DisplayName("카드 합 계산 확인")
    @Test
    void sumTest() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardNumber.THREE, CardFigure.CLOVER));
        cards.add(new Card(CardNumber.KING, CardFigure.CLOVER));

        Point point = new Point(cards);

        int expected = 13;
        int actual = point.getPoint();
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("카드 합 계산 확인 - ACE가 1이여야 할 때")
    @Test
    void sumTest_AceIs1() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardNumber.ACE, CardFigure.CLOVER));
        cards.add(new Card(CardNumber.KING, CardFigure.CLOVER));
        cards.add(new Card(CardNumber.QUEEN, CardFigure.HEART));

        Point point = new Point(cards);

        int expected = 21;
        int actual = point.getPoint();
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("카드 합 계산 확인 - ACE가 11이여야 할 때")
    @Test
    void sumTest_AceIs11() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardNumber.ACE, CardFigure.CLOVER));
        cards.add(new Card(CardNumber.KING, CardFigure.CLOVER));

        Point point = new Point(cards);

        int expected = 21;
        int actual = point.getPoint();
        AssertionsForClassTypes.assertThat(actual).isEqualTo(expected);
    }
}
