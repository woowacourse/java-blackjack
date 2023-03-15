package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreTest {

    @ParameterizedTest
    @MethodSource("compareScore")
    @DisplayName("isLessThan() : 파라미터로 들어온 점수보다 낮으면 true를 반환한다.")
    void test_isLessThan(final Score origin, final Score other, final boolean isLess) throws Exception {
        //when & then
        assertEquals(isLess, origin.isLessThan(other));
    }

    static Stream<Arguments> compareScore() {
        //true
        final Score origin1 = new Score(51);
        final Score other1 = new Score(100);

        //false
        final Score origin2 = new Score(100);
        final Score other2 = new Score(51);

        //true
        final Score origin3 = new Score(1);
        final Score other3 = new Score(2);

        //false
        final Score origin4 = new Score(1000);
        final Score other4 = new Score(1000);

        return Stream.of(
                Arguments.of(origin1, other1, true),
                Arguments.of(origin2, other2, false),
                Arguments.of(origin3, other3, true),
                Arguments.of(origin4, other4, false)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "22 -> true",
            "21 -> false",
            "20 -> false",
            "1 -> false"
    }, delimiterString = " -> ")
    @DisplayName("isBust() : 점수가 21 점 초과이면 bust가 된다.")
    void test_isBust(final int sum, final boolean isBust) throws Exception {
        //when
        final Score score = new Score(sum);

        //then
        assertEquals(isBust, score.isBust());
    }

    @Test
    @DisplayName("canMoreCard() : 점수가 21점 미만아면 카드를 더 받을 수 있다.")
    void test_canMoreCard() throws Exception {
        // given
        final Score score = new Score(20);

        // when & then
        assertTrue(score.canMoreCard());
    }

    @ParameterizedTest
    @MethodSource("compareEqualScore")
    @DisplayName("isLessEqualThan() : 파라미터로 들어온 점수보다 작거나 같으면 true를 반환한다.")
    void test_isLessEqualThen(final Score origin, final Score other, final boolean isLess) throws Exception {
        //when & then
        assertEquals(isLess, origin.isLessEqualThan(other));
    }

    static Stream<Arguments> compareEqualScore() {
        //true
        final Score origin1 = new Score(1);
        final Score other1 = new Score(2);

        //false
        final Score origin2 = new Score(21);
        final Score other2 = new Score(21);

        //true
        final Score origin3 = new Score(16);
        final Score other3 = new Score(15);

        //false
        final Score origin4 = new Score(16);
        final Score other4 = new Score(17);

        return Stream.of(
                Arguments.of(origin1, other1, true),
                Arguments.of(origin2, other2, true),
                Arguments.of(origin3, other3, false),
                Arguments.of(origin4, other4, true)
        );
    }

    @Test
    @DisplayName("isGreaterThan() : 파라미터로 들어온 점수보다 높으면 true를 반환한다.")
    void test_isGreaterThan() throws Exception {
        //given
        final Score origin1 = new Score(21);
        final Score other1 = new Score(21);

        final Score origin2 = new Score(21);
        final Score other2 = new Score(20);

        //when & then
        assertFalse(origin1.isGreaterThan(other1));
        assertTrue(origin2.isGreaterThan(other2));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "21,21,42",
            "21,20,41"
    })
    @DisplayName("plus() : 두 점수를 더할 수 있다.")
    void test_add(final int value, final int other, final int sum) throws Exception {
        //given
        final Score origin = new Score(value);
        final Score resultScore = new Score(sum);

        //when & then
        assertEquals(origin.plus(new Score(other)), resultScore);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "10,20",
            "12,12",
            "11,21",
            "13,13",
            "14,14",
    })
    @DisplayName("plusTenIfNotBurst() : 다른 숫자를 더 할 때 bust 당하지 않으면 그 숫자를 더해줄 수 있다.")
    void test_plusIfNotBurst(final int value, final int sum) throws Exception {
        //given
        final Score origin = new Score(value);
        final Score result = new Score(sum);

        //when & then
        assertEquals(origin.plusSoftHand(), result);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "21 -> true",
            "20 -> false",
            "0 -> false",
    }, delimiterString = " -> ")
    @DisplayName("isSumTwentyOne() : 점수가 21이다.")
    void test_isBlackjack(final int value, final boolean isBlackjack) throws Exception {
        //when & then
        assertEquals(new Score(value).isSumTwentyOne(), isBlackjack);
    }

    @ParameterizedTest
    @CsvSource({
            "21,21,true",
            "20,20,true",
            "21,20,false"
    })
    @DisplayName("isSame() : 같은 점수일 경우 true를 반환한다.")
    void test_isSame(final int value1, final int value2, final boolean result) throws Exception {
        //given
        final Score score1 = new Score(value1);
        final Score score2 = new Score(value2);

        //when & then
        assertEquals(result, score1.isSame(score2));
    }
}
