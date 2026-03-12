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
        final Card ace = new Card(CardSuit.SPADE, CardRank.ACE);
        final Card two = new Card(CardSuit.SPADE, CardRank.TWO);
        final Card five = new Card(CardSuit.SPADE, CardRank.FIVE);
        final Card nine = new Card(CardSuit.SPADE, CardRank.NINE);

        final Card ten = new Card(CardSuit.SPADE, CardRank.TEN);
        final Card jack = new Card(CardSuit.SPADE, CardRank.JACK);
        final Card queen = new Card(CardSuit.SPADE, CardRank.QUEEN);
        final Card king = new Card(CardSuit.SPADE, CardRank.KING);

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
        final Card ace = new Card(CardSuit.SPADE, CardRank.ACE);
        final Card ten = new Card(CardSuit.SPADE, CardRank.TEN);
        final Card jack = new Card(CardSuit.SPADE, CardRank.JACK);
        final Card queen = new Card(CardSuit.SPADE, CardRank.QUEEN);
        final Card king = new Card(CardSuit.SPADE, CardRank.KING);
        final Card two = new Card(CardSuit.SPADE, CardRank.TWO);

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
        hand.addCard(new Card(CardSuit.SPADE, CardRank.ACE));
        hand.addCard(new Card(CardSuit.SPADE, CardRank.TWO));
        hand.addCard(new Card(CardSuit.SPADE, CardRank.ACE));
        hand.addCard(new Card(CardSuit.SPADE, CardRank.EIGHT));

        // when
        final int score = hand.getScore();

        // then
        assertThat(score).isEqualTo(12);
    }
}
