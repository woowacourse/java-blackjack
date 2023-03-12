package domain.hand;

import domain.Score;
import domain.card.Card;
import domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.card.CardShape.*;
import static domain.card.CardValue.ACE;
import static domain.card.CardValue.FOUR;
import static domain.card.CardValue.JACK;
import static domain.card.CardValue.NINE;
import static domain.card.CardValue.SEVEN;
import static domain.card.CardValue.SIX;
import static domain.card.CardValue.TEN;
import static domain.card.CardValue.THREE;
import static domain.card.CardValue.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Hand 는")
class HandTest {

    @Test
    void 카드를_추가할_수_있다() {
        // given
        final Hand hand = new Hand();

        // when
        final int beforeSize = hand.cards().size();
        hand.addCard(new Card(CLOVER, FOUR));

        // then
        assertThat(hand.cards().size()).isEqualTo(beforeSize + 1);
    }

    @ParameterizedTest(name = "카드 목록이 {0} 일 때, 총합은 {1}다.")
    @CsvSource(value = {
            "TWO+THREE = 5",
            "FIVE+SIX = 11",
            "TWO+TWO = 4",
            "TEN+TEN = 20",
    }, delimiterString = " = ")
    void 자신이_가진_카드의_합을_구할_수_있다(final String values, final int totalScore) {
        // given
        final String[] split = values.split("\\+");

        final Hand hand = new Hand();

        // when
        hand.addCard(new Card(CLOVER, valueOf(split[0])));
        hand.addCard(new Card(CLOVER, valueOf(split[1])));

        // then
        assertThat(hand.calculate()).isEqualTo(new Score(totalScore));
    }

    @ParameterizedTest(name = "킹, 퀸, 잭은 10으로 계산한다")
    @CsvSource(value = {
            "KING+QUEEN = 20",
            "KING+JACK = 20",
            "QUEEN+JACK = 20",
            "KING+THREE = 13",
            "QUEEN+THREE = 13",
            "JACK+SIX = 16",
    }, delimiterString = " = ")
    void 킹_퀸_잭은_10으로_계산한다(final String values, final int totalScore) {
        // given
        final String[] split = values.split("\\+");
        final Hand hand = new Hand();

        hand.addCard(new Card(CLOVER, valueOf(split[0])));
        hand.addCard(new Card(CLOVER, valueOf(split[1])));

        // when & then
        assertThat(hand.calculate()).isEqualTo(new Score(totalScore));
    }

    @ParameterizedTest(name = "[{index}] ACE 는 이전까지의 총합이 10 이하면 11로 계산한다")
    @MethodSource("isSoftHand")
    void ACE_는_이전까지의_총합이_10_이하면_11로_계산한다(final Hand hand, final int totalScore) {
        // then
        assertThat(hand.calculate()).isEqualTo(new Score(totalScore));
    }

    static Stream<Arguments> isSoftHand() {

        // 10 + [11] = 21
        final Hand hand1 = new Hand();
        hand1.addCard(new Card(CLOVER, TEN));
        hand1.addCard(new Card(CLOVER, ACE));

        // 10 + 10 + [1] = 21
        final Hand hand2 = new Hand();
        hand2.addCard(new Card(CLOVER, JACK));
        hand2.addCard(new Card(CLOVER, TEN));

        hand2.addCard(new Card(CLOVER, ACE));

        // [11] + 9 + [1] = 21
        final Hand hand3 = new Hand();
        hand3.addCard(new Card(CLOVER, ACE));
        hand3.addCard(new Card(CLOVER, NINE));

        hand3.addCard(new Card(CLOVER, ACE));

        // [11] + 6 + 3 = 20
        final Hand hand4 = new Hand();
        hand4.addCard(new Card(CLOVER, SIX));
        hand4.addCard(new Card(CLOVER, THREE));

        hand4.addCard(new Card(CLOVER, ACE));

        // [11] + 10 = 21
        final Hand hand5 = new Hand();
        hand5.addCard(new Card(CLOVER, ACE));
        hand5.addCard(new Card(CLOVER, TEN));

        // 10 + [1] + 7 = 18
        final Hand hand6 = new Hand();
        hand6.addCard(new Card(CLOVER, TEN));
        hand6.addCard(new Card(CLOVER, ACE));

        hand6.addCard(new Card(SPADE, SEVEN));

        return Stream.of(
                Arguments.of(hand1, 21),
                Arguments.of(hand2, 21),
                Arguments.of(hand3, 21),
                Arguments.of(hand4, 20),
                Arguments.of(hand5, 21),
                Arguments.of(hand6, 18)
        );
    }

    @Test
    void 총합이_20_이하면_카드를_더_받을_수_있는_상태이다() {
        // given
        final Hand hand = new Hand();
        hand.addCard(new Card(CLOVER, TEN));
        hand.addCard(new Card(CLOVER, TEN));

        // when & then
        assertTrue(hand.canMoreCard());
    }

    @Test
    void 총합이_21_이상이면_카드를_더_받을_수_없는_상태이다() {
        // given
        final Hand hand = new Hand();
        hand.addCard(new Card(CLOVER, ACE));
        hand.addCard(new Card(SPADE, TEN));

        // when & then
        assertFalse(hand.canMoreCard());
    }

    @Test
    void 총합이_21_초과이면_버스트_된다() {
        // given
        final Hand hand = new Hand();
        hand.addCard(new Card(CLOVER, TEN));
        hand.addCard(new Card(SPADE, TEN));

        hand.addCard(new Card(DIAMOND, TEN));

        // when & then
        assertTrue(hand.isBust());
    }

    @Test
    void 총합이_21_이하이면_버스트_아니다() {
        // given
        final Hand hand = new Hand();
        hand.addCard(new Card(CLOVER, TEN));
        hand.addCard(new Card(CLOVER, ACE));

        // when & then
        assertFalse(hand.isBust());
    }

    @Test
    @DisplayName("firstCard() : 처음 카드를 받을 때, 첫 번째 카드를 보여줄 수 있다.")
    void test_firstCard() {
        // given
        final Hand hand = new Hand();

        hand.addCard(new Card(CLOVER, TEN));
        hand.addCard(new Card(CLOVER, SEVEN));

        // when & then
        assertEquals(hand.firstCard(), new Card(CLOVER, TEN));
    }

    @Test
    @DisplayName("secondCard() : 처음 카드를 받을 때, 두 번째 카드를 보여줄 수 있다.")
    void test_secondCard() throws Exception {
        // given
        final Hand hand = new Hand();

        hand.addCard(new Card(CLOVER, TEN));
        hand.addCard(new Card(CLOVER, SEVEN));

        // when & then
        assertEquals(hand.secondCard(), new Card(CLOVER, SEVEN));
    }

    @ParameterizedTest
    @MethodSource("isBlackjack")
    @DisplayName("isBlackjack() : 총합이 21이면 블랙잭이다.")
    void test_isBlackjack(final Hand hand,
                          final boolean isBlackjack) throws Exception {
        //when & then
        assertEquals(hand.isBlackjack(), isBlackjack);
    }

    static Stream<Arguments> isBlackjack() {

        // 21
        final Hand hand1 = new Hand();
        hand1.addCard(new Card(CLOVER, TEN));
        hand1.addCard(new Card(CLOVER, ACE));

        // 21
        final Hand hand2 = new Hand();
        hand2.addCard(new Card(CLOVER, SIX));
        hand2.addCard(new Card(CLOVER, THREE));
        hand2.addCard(new Card(CLOVER, ACE));
        hand2.addCard(new Card(SPADE, ACE));

        return Stream.of(
                Arguments.of(hand1, true),
                Arguments.of(hand2, false)
        );
    }
}
