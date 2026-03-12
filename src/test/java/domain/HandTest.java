package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import domain.card.Hand;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    private static final Card ace = new Card(CardSuit.SPADE, CardRank.ACE);
    private static final Card two = new Card(CardSuit.SPADE, CardRank.TWO);
    private static final Card five = new Card(CardSuit.SPADE, CardRank.FIVE);
    private static final Card eight = new Card(CardSuit.SPADE, CardRank.EIGHT);
    private static final Card nine = new Card(CardSuit.SPADE, CardRank.NINE);

    private static final Card ten = new Card(CardSuit.SPADE, CardRank.TEN);
    private static final Card jack = new Card(CardSuit.SPADE, CardRank.JACK);
    private static final Card queen = new Card(CardSuit.SPADE, CardRank.QUEEN);
    private static final Card king = new Card(CardSuit.SPADE, CardRank.KING);

    @Test
    @DisplayName("패에 카드를 추가한다.")
    public void 패에_카드_추가_성공() {
        // given
        final Card card = new Card(CardSuit.SPADE, CardRank.ACE);
        final Hand hand = new Hand();

        // when
        hand.addCard(card);

        // then
        assertThat(hand.getHand()).contains(card);
    }


    @ParameterizedTest
    @MethodSource("새_카드_추가_시_합산_테스트케이스")
    @DisplayName("패에 카드가 추가되면 점수를 재계산한다.")
    public void 점수_계산_정상_동작(final List<Card> cards, final int total) {
        // given
        final Hand hand = new Hand();
        int currentScore = hand.getScore();

        // then
        for (final Card card : cards) {
            hand.addCard(card);
            assertThat(currentScore).isNotEqualTo(hand.getScore());
            currentScore = hand.getScore();
        }

        assertThat(currentScore).isEqualTo(total);
    }

    private static Stream<Arguments> 새_카드_추가_시_합산_테스트케이스() {
        return Stream.of(
                Arguments.of(List.of(ace, ten, ace), 12),            // 합이 12
                Arguments.of(List.of(ace, jack), 21),                // 합이 21
                Arguments.of(List.of(king, queen), 20),              // 합이 20
                Arguments.of(List.of(jack, king, ace), 21),          // 합이 21
                Arguments.of(List.of(five, queen, king), 25),        // 합이 25
                Arguments.of(List.of(jack, queen, king), 30)         // 합이 30
//                Arguments.of(List.of(ace, two, queen, nine), 22)      // queen이 나올 때, 13 -> 13 동일해지는 케이스
        );
    }


    @ParameterizedTest
    @MethodSource("버스트_여부_판단_테스트케이스")
    @DisplayName("플레이어는 패에 있는 카드 합이 21 초과라면 버스트다.")
    public void 플레이어_버스트_판단(final List<Card> cards, final boolean result) {
        // given
        final Hand hand = new Hand();
        for (final Card card : cards) {
            hand.addCard(card);
        }

        // when
        final boolean bust = hand.isBust();

        // then
        assertThat(bust).isEqualTo(result);

    }

    private static Stream<Arguments> 버스트_여부_판단_테스트케이스() {
        return Stream.of(
                Arguments.of(List.of(ace, ten, ace), false),            // 합이 12
                Arguments.of(List.of(ace, jack), false),                // 합이 21
                Arguments.of(List.of(king, queen), false),              // 합이 20
                Arguments.of(List.of(jack, king, ace), false),          // 합이 21
                Arguments.of(List.of(five, queen, king), true),         // 합이 25
                Arguments.of(List.of(jack, queen, king), true),         // 합이 30
                Arguments.of(List.of(ace, two, queen, nine), true)      // 합이 22
        );
    }


    @ParameterizedTest
    @MethodSource("블랙잭_여부_판단_테스트케이스")
    @DisplayName("가진 패의 블랙잭 여부를 판단한다.")
    public void 블랙잭_여부_판단(final List<Card> cards, final boolean result) {
        // given
        final Hand hand = new Hand();
        for (final Card card : cards) {
            hand.addCard(card);
        }

        // when
        final boolean isBlackjack = hand.isBlackjack();

        // then
        assertThat(isBlackjack).isEqualTo(result);

    }

    private static Stream<Arguments> 블랙잭_여부_판단_테스트케이스() {
        return Stream.of(
                Arguments.of(List.of(ace, ten), true),
                Arguments.of(List.of(ace, jack), true),
                Arguments.of(List.of(ace, queen), true),
                Arguments.of(List.of(ace, king), true),
                Arguments.of(List.of(ace, two), false),
                Arguments.of(List.of(ace), false),
                Arguments.of(List.of(ace, two, queen), false)
        );
    }

    @Test
    @DisplayName("플레이어가 가지고 있는 카드 점수를 반환한다.")
    public void 카드_점수_반환_성공() {
        // given
        final Hand hand = new Hand();
        hand.addCard(ace);
        hand.addCard(two);
        hand.addCard(ace);
        hand.addCard(eight);

        // when
        final int score = hand.getScore();

        // then
        assertThat(score).isEqualTo(1 + 2 + 1 + 8);
    }
}
