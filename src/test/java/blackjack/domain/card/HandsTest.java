package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Hands;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandsTest {
    @DisplayName("카드의 합을 계산한다")
    @Test
    void calculateSumTest() {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardValue.TEN);
        Card card2 = new Card(CardShape.HEART, CardValue.EIGHT);

        Hands hands = new Hands();

        hands.addNewCard(card1);
        hands.addNewCard(card2);

        // when
        int result = hands.calculateSum();

        // then
        assertThat(result).isEqualTo(18);
    }

    @DisplayName("카드의 합이 특정 값 이하이면 True를 반환한다")
    @Test
    void isSumBelowTest1() {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardValue.TEN);
        Card card2 = new Card(CardShape.HEART, CardValue.EIGHT);

        Hands hands = new Hands();

        hands.addNewCard(card1);
        hands.addNewCard(card2);

        // when
        boolean result = hands.isSumBelow(21);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("카드의 합이 특정 값 초과이면 False를 반환한다")
    @Test
    void isSumBelowTest2() {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardValue.TEN);
        Card card2 = new Card(CardShape.HEART, CardValue.EIGHT);
        Card card3 = new Card(CardShape.HEART, CardValue.KING);

        Hands hands = new Hands();

        hands.addNewCard(card1);
        hands.addNewCard(card2);
        hands.addNewCard(card3);

        // when
        boolean result = hands.isSumBelow(21);

        // then
        assertThat(result).isFalse();
    }
}
