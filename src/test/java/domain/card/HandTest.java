package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static domain.Fixtures.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class HandTest {

    @Test
    @DisplayName("핸드에 카드가 저장된다")
    void makeHandTest() {
        List<Card> cards = List.of(
                CLOVER_EIGHT,
                DIAMOND_ACE,
                HEART_KING
        );

        Hand hand = new Hand(cards);

        assertThat(hand.getCards())
                .isEqualTo(cards);
    }

    @Test
    @DisplayName("카드가 없을때에는 합이 0이다")
    void sumHandWhenCardsNotExist() {
        List<Card> cards = Collections.emptyList();

        Hand hand = new Hand(cards);

        assertThat(hand.score())
                .isEqualTo(new Score(0));
    }

    @Test
    @DisplayName("카드가 있을때에는 합이 카드 값에 따라 결정된다")
    void sumHandWhenCardsExist() {
        List<Card> cards = List.of(
                CLOVER_FIVE,
                HEART_EIGHT
        );

        Hand hand = new Hand(cards);

        assertThat(hand.score())
                .isEqualTo(new Score(13));
    }

    @Test
    @DisplayName("에이스카드는 나머지 카드의 합이 10보다 크면 1로 결정된다")
    void decideAceSumOver() {
        List<Card> cards = List.of(
                CLOVER_FIVE,
                CLOVER_EIGHT,
                CLOVER_ACE
        );

        Hand hand = new Hand(cards);

        assertThat(hand.score())
                .isEqualTo(new Score(14));
    }

    @Test
    @DisplayName("에이스카드가 먼저 뽑혀도 나머지 카드의 합이 10보다 크면 1로 결정된다")
    void decideAceSumOverWhenFirstDraw() {
        List<Card> cards = List.of(
                CLOVER_ACE,
                CLOVER_FIVE,
                HEART_EIGHT

        );

        Hand hand = new Hand(cards);

        assertThat(hand.score())
                .isEqualTo(new Score(14));
    }

    @Test
    @DisplayName("에이스카드는 나머지 카드의 합이 10보다 작으면 11로 결정된다")
    void decideAceSumUnder() {
        List<Card> cards = List.of(
                CLOVER_FIVE,
                HEART_FOUR,
                CLOVER_ACE
        );

        Hand hand = new Hand(cards);

        assertThat(hand.score())
                .isEqualTo(new Score(20));
    }

    @Test
    @DisplayName("에이스가 2장 이상일 때 카드의 합은 21을 넘지 않는 가장 큰 수로 결정된다.")
    void decideDoubleAceSumUnder() {
        List<Card> cards = List.of(
                CLOVER_ACE,
                SPADE_ACE,
                CLOVER_FOUR,
                HEART_FOUR
        );

        Hand hand = new Hand(cards);

        assertThat(hand.score())
                .isEqualTo(new Score(20));
    }

    @Test
    @DisplayName("카드가 잘 들어가는지 테스트한다.")
    void addTest() {
        Hand hand = new Hand(Collections.emptyList());
        Card card = CLOVER_FOUR;

        hand.add(card);

        assertThat(hand.getCards()).contains(card);
    }

    @DisplayName("카드의 숫자가 10, 10, 5 이면 버스트이다.")
    @Test
    void isBust_TenTenFive_True() {
        Hand hand = new Hand(List.of(DIAMOND_TEN,
                CLOVER_TEN));

        hand.add(DIAMOND_FIVE);

        assertThat(hand.isBust()).isTrue();
    }

    @DisplayName("카드의 숫자가 ACE, 10, 5, 10 이면 버스트이다.")
    @Test
    void isBust_AceTenFiveTen_True() {
        Hand hand = new Hand(List.of(DIAMOND_ACE,
                CLOVER_TEN));

        hand.add(DIAMOND_FIVE);
        hand.add(DIAMOND_TEN);

        assertThat(hand.isBust()).isTrue();
    }

    @DisplayName("카드의 숫자가 10, 10, ACE 이면 버스트가 아니다.")
    @Test
    void isBust_TenTenAce_False() {
        Hand hand = new Hand(List.of(
                DIAMOND_TEN,
                CLOVER_TEN,
                SPADE_ACE
        ));

        assertThat(hand.isBust()).isFalse();
    }

    @Test
    void isBlackjack() {
        Hand hand = new Hand(SPADE_ACE, SPADE_TEN);

        assertThat(hand.isBlackjack()).isTrue();
    }
}
