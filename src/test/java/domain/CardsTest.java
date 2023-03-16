package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @Test
    @DisplayName("Cards에 Card를 추가한다.")
    void add() {
        Cards cards = new Cards();

        cards.add(new Card(CardNumber.ACE, CardPattern.SPADE));

        Assertions.assertThat(cards.getSum()).isEqualTo(11);
    }

    @Test
    @DisplayName("카드목록의 합을 계산한다.")
    void sumTest() {
        Card card1 = new Card(CardNumber.KING, CardPattern.SPADE);
        Card card2 = new Card(CardNumber.KING, CardPattern.DIAMOND);
        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);

        Assertions.assertThat(cards.getSum()).isEqualTo(20);
    }

    @Test
    @DisplayName("카드목록에 ACE가 존재하고 카드값의 합이 21 초과시 ACE의 값을 1로 계산한다")
    void changeAceValue() {
        Card card1 = new Card(CardNumber.ACE, CardPattern.DIAMOND);
        Card card2 = new Card(CardNumber.ACE, CardPattern.SPADE);
        Card card3 = new Card(CardNumber.ACE, CardPattern.HEART);
        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Assertions.assertThat(cards.getSum()).isEqualTo(13);
    }

    @Test
    @DisplayName("카드가 2장이고, 합이 21인 경우 블랙잭이다.")
    void isBlackJack() {
        Card card1 = new Card(CardNumber.KING, CardPattern.SPADE);
        Card card2 = new Card(CardNumber.ACE, CardPattern.DIAMOND);
        Cards cards = new Cards();
        cards.add(card1);
        cards.add(card2);

        assertThat(cards.isBlackJack()).isTrue();
    }

}
