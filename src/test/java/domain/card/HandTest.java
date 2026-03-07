package domain.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HandTest {

    static Stream<HandScoreTestCase> handScoreProvider() {
        return Stream.of(
                new HandScoreTestCase(
                        List.of(new Card(Rank.TWO, Suit.DIAMOND), new Card(Rank.JACK, Suit.DIAMOND)),
                        12,
                        "Ace 없는 Hand에 대해서 점수를 합산한다."
                ),
                new HandScoreTestCase(
                        List.of(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.JACK, Suit.DIAMOND)),
                        21,
                        "합이 21이하인 경우 ACE를 11로 계산한다."
                ),
                new HandScoreTestCase(
                        List.of(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.ACE, Suit.HEART)),
                        12,
                        "합이 21초과인 경우 ACE를 1로 계산한다."
                )
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("handScoreProvider")
    void calculateScoreTests(HandScoreTestCase testCase) {
        // given
        Hand hand = new Hand();
        testCase.cards().forEach(hand::drawCard);

        // when - then
        assertEquals(testCase.expectedScore(), hand.calculateScore(), testCase.displayName());
    }

    private record HandScoreTestCase(List<Card> cards, int expectedScore, String displayName) {}

    @Test
    @DisplayName("점수 합산이 21 초과이면 버스트로 판단한다.")
    void Ace_21초과_버스트_판단() {
        // given
        List<Card> cards = List.of(new Card(Rank.EIGHT, Suit.DIAMOND), new Card(Rank.KING, Suit.HEART), new Card(Rank.JACK, Suit.DIAMOND));
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.drawCard(card);
        }

        // when - then
        Assertions.assertTrue(hand.isBust());
    }

    @Test
    @DisplayName("Hand의 패가 블랙잭인지 판단한다.")
    void 블랙잭_판단() {
        // given
        List<Card> cards = List.of(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.KING, Suit.HEART));
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.drawCard(card);
        }

        // when - then
        Assertions.assertTrue(hand.isBlackJack());
    }

    @Test
    @DisplayName("3장 이상의 패로 점수 21을 만든 경우 블랙잭이 아니라고 판단한다.")
    void 블랙잭_3장_판단() {
        // given
        List<Card> cards = List.of(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.EIGHT, Suit.HEART), new Card(Rank.TWO, Suit.DIAMOND));
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.drawCard(card);
        }

        // when - then
        Assertions.assertFalse(hand.isBlackJack());
    }

    @Test
    @DisplayName("Ace가 Hand에 있지만 점수가 21이 되지 않는 경우 블랙잭이 아니라고 판단한다.")
    void 블랙잭_점수_판단() {
        // given
        List<Card> cards = List.of(new Card(Rank.ACE, Suit.DIAMOND), new Card(Rank.EIGHT, Suit.HEART));
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.drawCard(card);
        }

        // when - then
        Assertions.assertFalse(hand.isBlackJack());
    }

    @Test
    @DisplayName("Hand에 있는 첫 카드를 반환한다.")
    void 첫_카드_반환() {
        // given
        Card firstCard = new Card(Rank.ACE, Suit.DIAMOND);
        Card secondCard = new Card(Rank.EIGHT, Suit.HEART);

        List<Card> cards = List.of(firstCard, secondCard);
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.drawCard(card);
        }

        // when - then
        assertEquals(hand.getFirstCard(), firstCard);
    }

    @Test
    @DisplayName("Hand에 있는 전체 카드를 반환한다.")
    void 전체_카드_반환() {
        // given
        Card firstCard = new Card(Rank.ACE, Suit.DIAMOND);
        Card secondCard = new Card(Rank.EIGHT, Suit.HEART);

        List<Card> cards = List.of(firstCard, secondCard);
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.drawCard(card);
        }

        // when - then
        assertEquals(hand.getCards().size(), 2);
    }

        @Test
    @DisplayName("Hand에 카드가 없을 때 첫 카드를 확인하려고 하면 예외를 발생한다.")
    void NULL_예외_처리_첫_카드() {
        // given
        Hand hand = new Hand();


        // when - then
        assertThrows(IllegalStateException.class, hand::getFirstCard);
    }

    @Test
    @DisplayName("Hand에 카드가 없을 때 전체 카드를 확인하려고 하면 예외를 발생한다.")
    void NULL_예외_처리_카드_전체() {
        // given
        Hand hand = new Hand();


        // when - then
        assertThrows(IllegalStateException.class, hand::getCards);
    }
}
