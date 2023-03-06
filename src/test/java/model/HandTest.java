package model;

import model.card.Card;
import model.card.Shape;
import model.card.Value;
import model.user.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    private static Hand hand;

    @BeforeEach
    void init() {
        hand = Hand.create();
        hand.receiveCard(new Card(Shape.DIAMOND, Value.KING));
        hand.receiveCard(new Card(Shape.DIAMOND, Value.SEVEN));
        hand.receiveCard(new Card(Shape.DIAMOND, Value.TWO));
    }
    @Test
    @DisplayName("플레이어가 가지고 있는 카드 값의 총 합을 반환한다.")
    void calculateTotalValue() {
        // when, then
        assertThat(hand.getTotalValue()).isEqualTo(19);
    }

    @Test
    @DisplayName("만약 21을 넘고 ACE를 가지고 있다면 10을 빼서 반환한다.")
    void calculateReturnTotalValueTest() {
        // given
        hand.receiveCard(new Card(Shape.DIAMOND, Value.ACE));

        // when, then
        assertThat(hand.getTotalValue()).isEqualTo(20);
    }
}
