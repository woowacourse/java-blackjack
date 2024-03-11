package domain.gamer;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    @DisplayName("지금까지 뽑은 카드 숫자의 합을 저장한다.")
    @Test
    void saveTotalCardNumbersTest() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardNumber.TWO, CardShape.HEART));
        hand.addCard(new Card(CardNumber.THREE, CardShape.SPADE));
        hand.addCard(new Card(CardNumber.FOUR, CardShape.SPADE));

        assertThat(hand.calculateScore()).isEqualTo(9);
    }

    @DisplayName("A를 제외한 나머지 카드의 합이 10 이하인 경우 A는 11로 계산한다.")
    @Test
    void calculateScoreWhenUnderThresholdTest() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardNumber.FIVE, CardShape.HEART));
        hand.addCard(new Card(CardNumber.FIVE, CardShape.SPADE));
        hand.addCard(new Card(CardNumber.ACE, CardShape.SPADE));

        assertThat(hand.calculateScore()).isEqualTo(21);
    }

    @DisplayName("A를 제외한 나머지 카드의 합이 10 초과인 경우 A는 1로 계산한다.")
    @Test
    void calculateScoreWhenOverThresholdTest() {
        Hand hand = new Hand();
        hand.addCard(new Card(CardNumber.KING, CardShape.HEART));
        hand.addCard(new Card(CardNumber.KING, CardShape.SPADE));
        hand.addCard(new Card(CardNumber.ACE, CardShape.SPADE));

        assertThat(hand.calculateScore()).isEqualTo(21);
    }
}
