package model.user;

import model.card.Card;
import model.user.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static model.card.Shape.CLOVER;
import static model.card.Shape.DIAMOND;
import static model.card.Shape.HEART;
import static model.card.Shape.SPADE;
import static model.card.Value.ACE;
import static model.card.Value.KING;
import static model.card.Value.SEVEN;
import static model.card.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;

class HandTest {

    private static Hand hand;

    @BeforeEach
    void init() {
        hand = Hand.create();
    }
    @Test
    @DisplayName("플레이어가 가지고 있는 카드 값의 총 합을 반환한다.")
    void calculateTotalValue() {
        // given
        hand.receiveCard(new Card(DIAMOND, KING));
        hand.receiveCard(new Card(DIAMOND, SEVEN));
        hand.receiveCard(new Card(DIAMOND, TWO));

        // when, then
        assertThat(hand.getTotalValue()).isEqualTo(19);
    }

    @Nested
    @DisplayName("Ace 예외 처리 관련 테스트를 진행한다.")
    class AceTest {

        @Test
        @DisplayName("에이스가 1장이면 11로 취급한다.")
        void calculateReturnTotalValue_whenOneAce() {
            // given
            hand.receiveCard(new Card(DIAMOND, ACE));

            // when, then
            assertThat(hand.getTotalValue()).isEqualTo(11);
        }

        @Test
        @DisplayName("에이스가 2장이면 12로 취급한다.")
        void calculateReturnTotalValue_whenTwoAce() {
            // given
            hand.receiveCard(new Card(DIAMOND, ACE));
            hand.receiveCard(new Card(HEART, ACE));

            // when, then
            assertThat(hand.getTotalValue()).isEqualTo(12);
        }

        @Test
        @DisplayName("에이스가 3장이면 13로 취급한다.")
        void calculateReturnTotalValue_whenThreeAce() {
            // given
            hand.receiveCard(new Card(DIAMOND, ACE));
            hand.receiveCard(new Card(HEART, ACE));
            hand.receiveCard(new Card(CLOVER, ACE));

            // when, then
            assertThat(hand.getTotalValue()).isEqualTo(13);
        }

        @Test
        @DisplayName("에이스가 4장이면 14로 취급한다.")
        void calculateReturnTotalValue_whenFourAce() {
            // given
            hand.receiveCard(new Card(DIAMOND, ACE));
            hand.receiveCard(new Card(HEART, ACE));
            hand.receiveCard(new Card(CLOVER, ACE));
            hand.receiveCard(new Card(SPADE, ACE));

            // when, then
            assertThat(hand.getTotalValue()).isEqualTo(14);
        }
    }
}
