package blackjack.domain.card;

import static blackjack.domain.TestBlackjackUtils.createCardHand;
import static blackjack.domain.TestCardFixture.*;
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
    void createHand() {
        Hand hand = createCardHand(threeCard, twoCard);

        assertThat(hand.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드팩의 합계를 구한다")
    void sumCardHand() {
        Hand hand = createCardHand(twoCard, jackCard);

        assertThat(hand.getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("카드팩의 합계를 구한다")
    void sumCardHandOtherCase() {
        Hand hand = createCardHand(fourCard, jackCard);

        assertThat(hand.getScore()).isEqualTo(14);
    }

    @Test
    @DisplayName("21점이 넘을 경우 버스트 이다.")
    void isBust() {
        Hand hand = createCardHand(kingCard, jackCard, twoCard);

        assertThat(hand.isBust()).isTrue();
    }

    @Test
    @DisplayName("21점이 넘지 않을 경우 버스트가 아니다.")
    void isNotBust() {
        Hand hand = createCardHand(kingCard, eightCard, twoCard);

        assertThat(hand.isBust()).isFalse();
    }

    @Test
    @DisplayName("카드 2장이고 21점이면 블랙잭이다.")
    void isBlackjack() {
        Hand hand = createCardHand(aceCard, tenCard);

        assertThat(hand.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("카드가 2장이 아니고 21점이면 블랙잭이 아니다.")
    void isNotBlackjack() {
        Hand hand = createCardHand(fourCard, fiveCard, tenCard, twoCard);

        assertThat(hand.isBlackjack()).isFalse();
    }

    @DisplayName("Ace가 포함된 경우 점수 계산이 정확한지 확인")
    @ParameterizedTest
    @MethodSource("hasAceCardHandScoreTestCase")
    void getScoreIsMakeSure(Hand hand, int expected) {
        assertThat(hand.getScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> hasAceCardHandScoreTestCase() {
        return Stream.of(
            Arguments.of(createCardHand(aceCard, tenCard), 21),
            Arguments.of(createCardHand(aceCard, kingCard), 21),
            Arguments.of(createCardHand(aceCard, jackCard), 21),
            Arguments.of(createCardHand(aceCard, queenCard), 21),
            Arguments.of(createCardHand(aceCard, aceCard, nineCard), 21),
            Arguments.of(createCardHand(aceCard, aceCard, aceCard, eightCard), 21),
            Arguments.of(createCardHand(aceCard, sevenCard), 18)
        );
    }
}
