package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardNumberType;
import card.CardShapeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("숫자와 모양이 같다면 같은 객체이다")
    void test1() {
        // given
        Card card1 = new Card(CardNumberType.SIX, CardShapeType.SPACE);
        Card card2 = new Card(CardNumberType.SIX, CardShapeType.SPACE);

        //when & then
        assertThat(card1).isEqualTo(card2);
    }

    @Test
    @DisplayName("숫자가 같고 모양이 다르면 다른 객체이다")
    void test2() {
        // given
        Card card1 = new Card(CardNumberType.SIX, CardShapeType.HEART);
        Card card2 = new Card(CardNumberType.SIX, CardShapeType.SPACE);

        //when & then
        assertThat(card1).isNotEqualTo(card2);
    }

    @Test
    @DisplayName("숫자가 다르고 모양이 같다면 다른 객체이다")
    void test3() {
        // given
        Card card1 = new Card(CardNumberType.SEVEN, CardShapeType.SPACE);
        Card card2 = new Card(CardNumberType.SIX, CardShapeType.SPACE);

        //when & then
        assertThat(card1).isNotEqualTo(card2);
    }

}
