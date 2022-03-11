package blackjack.domain;

import static blackjack.CardCreationUtil.createHand;
import static blackjack.domain.card.Denomination.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    @Test
    @DisplayName("카드를 가져와서 나의 패(Hand)를 만든다")
    void make_hand() {
        Hand hand = createHand(TWO, JACK);

        assertThat(hand.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드팩의 합계를 구한다")
    void sum_hand() {
        Hand hand = createHand(TWO, JACK);

        assertThat(hand.getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("21점이 넘을 경우 버스트 이다.")
    void isBust() {
        Hand hand = createHand(KING, JACK, TWO);

        assertThat(hand.isBust()).isTrue();
    }

    @Test
    @DisplayName("21점이 넘지 않을 경우 버스트가 아니다.")
    void isNotBust() {
        Hand hand = createHand(KING, EIGHT, TWO);

        assertThat(hand.isBust()).isFalse();
    }

    @Test
    @DisplayName("카드 2장이고 21점이면 블랙잭이다.")
    void isBlackjack() {
        Hand hand = createHand(ACE, TEN);

        assertThat(hand.isBlackjack()).isTrue();
    }

    @DisplayName("Ace가 포함된 경우 점수 계산이 정확한지 확인")
    @ParameterizedTest
    @MethodSource("supplyScoresWhenContainingAceCard")
    void calculate_score_when_ace_included(Hand hand, int expected) {
        assertThat(hand.getScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> supplyScoresWhenContainingAceCard() {
        return Stream.of(
                Arguments.of(createHand(ACE, ACE, ACE, NINE), 12),
                Arguments.of(createHand(ACE, ACE), 12),
                Arguments.of(createHand(ACE, ACE, ACE), 13),
                Arguments.of(createHand(ACE, ACE, ACE, ACE), 14),
                Arguments.of(createHand(ACE, ACE, ACE, TWO), 15),
                Arguments.of(createHand(ACE, SEVEN, KING), 18),
                Arguments.of(createHand(ACE, TEN), 21),
                Arguments.of(createHand(ACE, ACE, NINE), 21),
                Arguments.of(createHand(ACE, ACE, ACE, EIGHT), 21)
        );
    }
}
