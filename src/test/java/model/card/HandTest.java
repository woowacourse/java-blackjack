package model.card;

import static model.card.CardFixture.CLOVER_ACE;
import static model.card.CardFixture.DIAMOND_ACE;
import static model.card.CardFixture.DIAMOND_FIVE;
import static model.card.CardFixture.DIAMOND_KING;
import static model.card.CardFixture.DIAMOND_SEVEN;
import static model.card.CardFixture.DIAMOND_SIX;
import static model.card.CardFixture.DIAMOND_TWO;
import static model.card.CardFixture.HEART_ACE;
import static model.card.CardFixture.SPADE_ACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import model.user.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
        hand.receiveCard(DIAMOND_KING);
        hand.receiveCard(DIAMOND_SEVEN);
        hand.receiveCard(DIAMOND_TWO);

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
            hand.receiveCard(DIAMOND_ACE);

            // when, then
            assertThat(hand.getTotalValue()).isEqualTo(11);
        }

        @Test
        @DisplayName("에이스가 2장이면 12로 취급한다.")
        void calculateReturnTotalValue_whenTwoAce() {
            // given
            hand.receiveCard(DIAMOND_ACE);
            hand.receiveCard(HEART_ACE);

            // when, then
            assertThat(hand.getTotalValue()).isEqualTo(12);
        }

        @Test
        @DisplayName("에이스가 3장이면 13로 취급한다.")
        void calculateReturnTotalValue_whenThreeAce() {
            // given
            hand.receiveCard(DIAMOND_ACE);
            hand.receiveCard(HEART_ACE);
            hand.receiveCard(CLOVER_ACE);

            // when, then
            assertThat(hand.getTotalValue()).isEqualTo(13);
        }

        @Test
        @DisplayName("에이스가 4장이면 14로 취급한다.")
        void calculateReturnTotalValue_whenFourAce() {
            // given
            hand.receiveCard(DIAMOND_ACE);
            hand.receiveCard(HEART_ACE);
            hand.receiveCard(CLOVER_ACE);
            hand.receiveCard(SPADE_ACE);

            // when, then
            assertThat(hand.getTotalValue()).isEqualTo(14);
        }
    }

    @Nested
    @DisplayName("블랙잭 여부를 확인한다.")
    class BlackJackTest {

        @Test
        @DisplayName("처음 카드가 두 장일 때블랙잭 여부를 확인한다.")
        void givenInitialCard_whenBlackJack_thenReturnTrue() {
            // given
            hand.receiveCard(DIAMOND_ACE);
            hand.receiveCard(DIAMOND_KING);

            // when, then
            assertAll(
                    () -> assertThat(hand.isBlackJack()).isTrue(),
                    () -> {
                        hand.receiveCard(CLOVER_ACE);
                        assertThat(hand.isBlackJack()).isFalse();
                    }
            );
        }

        @Test
        @DisplayName("카드를 추가적으로 받았을 경우 블랙잭 여부를 확인한다.")
        void givenThreeCards_whenBlackJack_thenReturnTrue() {
            // given
            hand.receiveCard(DIAMOND_FIVE);
            hand.receiveCard(DIAMOND_SIX);
            hand.receiveCard(DIAMOND_KING);

            // when, then
            assertThat(hand.isBlackJack()).isFalse();
        }
    }
}
