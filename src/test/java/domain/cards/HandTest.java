package domain.cards;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {

    @DisplayName("지금까지 뽑은 카드 숫자의 합을 반환한다.")
    @Test
    void saveTotalCardNumbersTest() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(CardNumber.TWO, CardShape.HEART));
        hand.addCard(new Card(CardNumber.THREE, CardShape.SPADE));
        hand.addCard(new Card(CardNumber.FOUR, CardShape.SPADE));

        assertThat(hand.calculateScore()).isEqualTo(9);
    }

    @DisplayName("지금까지 뽑은 카드 숫자의 합이 21 미만인지 알려준다.")
    @Test
    void lowerThanThresholdTest() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(CardNumber.TWO, CardShape.HEART));
        hand.addCard(new Card(CardNumber.THREE, CardShape.SPADE));
        hand.addCard(new Card(CardNumber.FOUR, CardShape.SPADE));

        assertThat(hand.hasScoreUnderBustThreshold()).isTrue();
    }

    @DisplayName("A 카드가 있는 경우 계산한 점수에 10을 더했을 때 bust 되지 않으면 10을 더한다.")
    @Test
    void calculateScoreWithAceWhenNotBustTest() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(CardNumber.FIVE, CardShape.HEART));
        hand.addCard(new Card(CardNumber.FIVE, CardShape.SPADE));
        hand.addCard(new Card(CardNumber.ACE, CardShape.SPADE));

        assertThat(hand.calculateScore()).isEqualTo(21);
    }

    @DisplayName("A 카드가 있는 경우 계산한 점수에 10을 더했을 때 bust 되면 그대로 반환한다.")
    @Test
    void calculateScoreWithAceWhenBustTest() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(CardNumber.KING, CardShape.HEART));
        hand.addCard(new Card(CardNumber.KING, CardShape.SPADE));
        hand.addCard(new Card(CardNumber.ACE, CardShape.SPADE));

        assertThat(hand.calculateScore()).isEqualTo(21);
    }

    @DisplayName("A 카드가 있는 경우 계산한 점수에 10을 더했을 때 bust 되면 그대로 반환한다. - A A")
    @Test
    void calculateScoreWithTwoAcesWhenBustTest() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(CardNumber.ACE, CardShape.HEART));
        hand.addCard(new Card(CardNumber.ACE, CardShape.SPADE));

        assertThat(hand.calculateScore()).isEqualTo(12);
    }

    @DisplayName("A 카드가 있는 경우 계산한 점수에 10을 더했을 때 bust 되면 그대로 반환한다. - A A K")
    @Test
    void calculateScoreWithTwoAcesKingWhenBustTest() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(CardNumber.ACE, CardShape.HEART));
        hand.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        hand.addCard(new Card(CardNumber.KING, CardShape.SPADE));

        assertThat(hand.calculateScore()).isEqualTo(12);
    }

    @DisplayName("A 카드가 있는 경우 계산한 점수에 10을 더했을 때 bust 되면 그대로 반환한다. - A A A")
    @Test
    void calculateScoreWithThreeAcesWhenBustTest() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(CardNumber.ACE, CardShape.HEART));
        hand.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        hand.addCard(new Card(CardNumber.ACE, CardShape.CLOVER));

        assertThat(hand.calculateScore()).isEqualTo(13);
    }

    @DisplayName("A 카드가 없는 경우 계산한 점수를 그대로 반환한다.")
    @Test
    void calculateScoreWhenOverThresholdTest() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(CardNumber.TWO, CardShape.HEART));
        hand.addCard(new Card(CardNumber.THREE, CardShape.SPADE));
        hand.addCard(new Card(CardNumber.FOUR, CardShape.SPADE));

        assertThat(hand.calculateScore()).isEqualTo(9);
    }

    @DisplayName("지금까지 뽑은 카드 숫자의 합이 16 이하인지 알려준다.")
    @Test
    void lowerThanCanHitThresholdTest() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(CardNumber.KING, CardShape.HEART));
        hand.addCard(new Card(CardNumber.SIX, CardShape.SPADE));

        assertThat(hand.hasScoreUnderHitThreshold()).isTrue();
    }

    @DisplayName("지금까지 뽑은 카드 숫자의 합이 17 이상인지 알려준다.")
    @Test
    void lowerThanCannotHitThresholdTest() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(CardNumber.KING, CardShape.HEART));
        hand.addCard(new Card(CardNumber.SEVEN, CardShape.SPADE));

        assertThat(hand.hasScoreUnderHitThreshold()).isFalse();
    }

    @DisplayName("갖고 있는 카드의 첫번째 카드를 뽑는다.")
    @Test
    void getFirstCard() {
        Hand hand = new Hand(new ArrayList<>());
        hand.addCard(new Card(CardNumber.KING, CardShape.HEART));
        hand.addCard(new Card(CardNumber.SEVEN, CardShape.SPADE));

        assertThat(hand.pickFirstCard()).isEqualTo(new Card(CardNumber.KING, CardShape.HEART));
    }
}
