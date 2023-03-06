package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @ParameterizedTest
    @MethodSource("compareScore")
    @DisplayName("isLessThan() : 파라미터로 들어온 점수보다 낮으면 true를 반환한다.")
    void test_isLessThan(final Score origin, final Score other, final boolean isLess) throws Exception {
        //when & then
        assertEquals(isLess, origin.isLessThan(other));
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
}
