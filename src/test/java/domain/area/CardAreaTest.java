package domain.area;

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
@DisplayName("CardArea 은")
class CardAreaTest {

    @Test
    void 카드를_추가할_수_있다() {
        // given
        final CardArea cardArea = new CardArea();

        // when
        final int beforeSize = cardArea.cards().size();
        cardArea.addCard(new Card(CardShape.CLOVER, FOUR));

        // then
        assertThat(cardArea.cards().size()).isEqualTo(beforeSize + 1);
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

        final CardArea cardArea = new CardArea();

        // when
        cardArea.addCard(new Card(CardShape.CLOVER, valueOf(split[0])));
        cardArea.addCard(new Card(CardShape.CLOVER, valueOf(split[1])));

        // then
        assertThat(cardArea.calculate()).isEqualTo(new Score(totalScore));
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
        final CardArea cardArea = new CardArea();

        cardArea.addCard(new Card(CardShape.CLOVER, valueOf(split[0])));
        cardArea.addCard(new Card(CardShape.CLOVER, valueOf(split[1])));

        // when & then
        assertThat(cardArea.calculate()).isEqualTo(new Score(totalScore));
    }

    @ParameterizedTest(name = "[{index}] ACE 는 이전까지의 총합이 10 이하면 11로 계산한다")
    @MethodSource("containsAceCardArea")
    void ACE_는_이전까지의_총합이_10_이하면_11로_계산한다(final CardArea cardArea, final int totalScore) {
        // then
        assertThat(cardArea.calculate()).isEqualTo(new Score(totalScore));
    }

    static Stream<Arguments> containsAceCardArea() {

        // 10 + [11] = 21
        final CardArea cardArea1 = new CardArea();
        cardArea1.addCard(new Card(CardShape.CLOVER, TEN));
        cardArea1.addCard(new Card(CardShape.CLOVER, ACE));

        // 10 + 10 + [1] = 21
        final CardArea cardArea2 = new CardArea();
        cardArea2.addCard(new Card(CardShape.CLOVER, JACK));
        cardArea2.addCard(new Card(CardShape.CLOVER, TEN));

        cardArea2.addCard(new Card(CardShape.CLOVER, ACE));

        // [11] + 9 + [1] = 21
        final CardArea cardArea3 = new CardArea();
        cardArea3.addCard(new Card(CardShape.CLOVER, ACE));
        cardArea3.addCard(new Card(CardShape.CLOVER, NINE));

        cardArea3.addCard(new Card(CardShape.CLOVER, ACE));

        // [11] + 6 + 3 = 20
        final CardArea cardArea4 = new CardArea();
        cardArea4.addCard(new Card(CardShape.CLOVER, SIX));
        cardArea4.addCard(new Card(CardShape.CLOVER, THREE));

        cardArea4.addCard(new Card(CardShape.CLOVER, ACE));

        // [11] + 10 = 21
        final CardArea cardArea5 = new CardArea();
        cardArea5.addCard(new Card(CardShape.CLOVER, ACE));
        cardArea5.addCard(new Card(CardShape.CLOVER, TEN));

        // 10 + [1] + 7 = 18
        final CardArea cardArea6 = new CardArea();
        cardArea6.addCard(new Card(CardShape.CLOVER, TEN));
        cardArea6.addCard(new Card(CardShape.CLOVER, ACE));

        cardArea6.addCard(new Card(CardShape.SPADE, SEVEN));

        return Stream.of(
                Arguments.of(cardArea1, 21),
                Arguments.of(cardArea2, 21),
                Arguments.of(cardArea3, 21),
                Arguments.of(cardArea4, 20),
                Arguments.of(cardArea5, 21),
                Arguments.of(cardArea6, 18)
        );
    }

    @Test
    void 총합이_20_이하면_카드를_더_받을_수_있는_상태이다() {
        // given
        final CardArea cardArea = new CardArea();
        cardArea.addCard(new Card(CardShape.CLOVER, TEN));
        cardArea.addCard(new Card(CardShape.CLOVER, TEN));

        // when & then
        assertTrue(cardArea.canMoreCard());
    }

    @Test
    void 총합이_21_이상이면_카드를_더_받을_수_없는_상태이다() {
        // given
        final CardArea cardArea = new CardArea();
        cardArea.addCard(new Card(CardShape.CLOVER, ACE));
        cardArea.addCard(new Card(CardShape.SPADE, TEN));

        // when & then
        assertFalse(cardArea.canMoreCard());
    }

    @Test
    void 총합이_21_초과이면_버스트_된다() {
        // given
        final CardArea cardArea = new CardArea();
        cardArea.addCard(new Card(CardShape.CLOVER, TEN));
        cardArea.addCard(new Card(CardShape.SPADE, TEN));

        cardArea.addCard(new Card(CardShape.DIAMOND, TEN));

        // when & then
        assertTrue(cardArea.isBust());
    }

    @Test
    void 총합이_21_이하이면_버스트_아니다() {
        // given
        final CardArea cardArea = new CardArea();
        cardArea.addCard(new Card(CardShape.CLOVER, TEN));
        cardArea.addCard(new Card(CardShape.CLOVER, ACE));

        // when & then
        assertFalse(cardArea.isBust());
    }

    @Test
    void 딜러는_첫_장만_보여줄_수_있다() {
        // given
        final CardArea cardArea = new CardArea();

        cardArea.addCard(new Card(CardShape.CLOVER, TEN));
        cardArea.addCard(new Card(CardShape.CLOVER, SEVEN));

        // when & then
        assertEquals(cardArea.firstCard(), new Card(CardShape.CLOVER, TEN));
    }

    @ParameterizedTest
    @MethodSource("isBlackjack")
    @DisplayName("isBlackjack() : 총합이 21이면 블랙잭이다.")
    void test_isBlackjack(final CardArea cardArea, final int calculateScore) throws Exception {
        //given
        final boolean isBlackjack = new Score(calculateScore).isBlackjack();

        //when & then
        assertEquals(cardArea.isBlackjack(), isBlackjack);
    }

    static Stream<Arguments> isBlackjack() {

        // 21
        final CardArea cardArea1 = new CardArea();
        cardArea1.addCard(new Card(CardShape.CLOVER, TEN));
        cardArea1.addCard(new Card(CardShape.CLOVER, ACE));

        // 20
        final CardArea cardArea2 = new CardArea();
        cardArea2.addCard(new Card(CardShape.CLOVER, SIX));
        cardArea2.addCard(new Card(CardShape.CLOVER, THREE));
        cardArea2.addCard(new Card(CardShape.CLOVER, ACE));

        return Stream.of(
                Arguments.of(cardArea1, 21),
                Arguments.of(cardArea2, 20)
        );
    }
}
