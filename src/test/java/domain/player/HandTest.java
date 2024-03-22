package domain.player;

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
        hand.addCard(Card.valueOf(CardNumber.TWO, CardShape.HEART));
        hand.addCard(Card.valueOf(CardNumber.THREE, CardShape.HEART));
        hand.addCard(Card.valueOf(CardNumber.FOUR, CardShape.HEART));

        assertThat(hand.calculateScore()).isEqualTo(9);
    }

    @DisplayName("A를 1로 계산한 모든 카드의 합에 10을 더해도 21을 넘지 않으면 A는 11로 계산한다.")
    @Test
    void calculateScoreWhenUnderThresholdTest() {
        Hand hand = new Hand();
        hand.addCard(Card.valueOf(CardNumber.FIVE, CardShape.HEART));
        hand.addCard(Card.valueOf(CardNumber.FIVE, CardShape.HEART));
        hand.addCard(Card.valueOf(CardNumber.ACE, CardShape.HEART));

        assertThat(hand.calculateScore()).isEqualTo(21);
    }

    @DisplayName("A를 1로 계산한 모든 카드의 합에 10을 더해 21을 넘으면 A는 1로 계산한다.")
    @Test
    void calculateScoreWhenOverThresholdTest() {
        Hand hand = new Hand();
        hand.addCard(Card.valueOf(CardNumber.ACE, CardShape.HEART));
        hand.addCard(Card.valueOf(CardNumber.ACE, CardShape.CLOVER));
        hand.addCard(Card.valueOf(CardNumber.TEN, CardShape.HEART));

        assertThat(hand.calculateScore()).isEqualTo(12);
    }
}
