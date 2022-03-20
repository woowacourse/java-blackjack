package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @Test
    @DisplayName("K클로버와 2하트가 손에 있으면 12를 반환하는지")
    void Calculate_Correct_Summation_Of_K_And_2() {
        Cards cards = new Cards();

        cards.addCard(Card.of(CardType.CLOVER, CardValue.TEN));
        cards.addCard(Card.of(CardType.HEART, CardValue.TWO));

        assertThat(cards.sum()).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스를 11로 적용하는지")
    void Select_Ace_Value_11() {
        Cards cards = new Cards();

        cards.addCard(Card.of(CardType.CLOVER, CardValue.ACE));
        cards.addCard(Card.of(CardType.HEART, CardValue.TWO));
        cards.addCard(Card.of(CardType.SPADE, CardValue.SEVEN));

        assertThat(cards.sum()).isEqualTo(20);
    }

    @Test
    @DisplayName("에이스를 1로 적용하는지")
    void Select_Ace_Value_1() {
        Cards cards = new Cards();

        cards.addCard(Card.of(CardType.CLOVER, CardValue.ACE));
        cards.addCard(Card.of(CardType.HEART, CardValue.EIGHT));
        cards.addCard(Card.of(CardType.SPADE, CardValue.SEVEN));

        assertThat(cards.sum()).isEqualTo(16);
    }
}
