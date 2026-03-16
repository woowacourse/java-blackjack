package domain;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class RankTest {

    @Test
    void 에이스가_11이_될_수_있으면_11을_적용한다() {
        Score result = Rank.sumWithAce(1, new Score(10));

        Assertions.assertEquals(result.value(), 21);
    }

    @Test
    void 에이스가_11이_될_수_없으면_1을_적용한다() {
        Score result = Rank.sumWithAce(1, new Score(11));

        Assertions.assertEquals(result.value(), 12);
    }

    @ParameterizedTest
    @MethodSource("calculateWithAces")
    void 에이스가_포함된_합계가_정상적으로_구해져야_한다(int aceAmount, int currentSum, int expect) {
        Score result = Rank.sumWithAce(aceAmount, new Score(currentSum));
        Assertions.assertEquals(result.value(), expect);
    }

    static Stream<Arguments> calculateWithAces() {
        return Stream.of(
                Arguments.of(2, 14, 16),
                Arguments.of(3, 10, 13),
                Arguments.of(2, 9, 21),
                Arguments.of(4, 0, 14),
                Arguments.of(3, 8, 21),
                Arguments.of(1, 0, 11),
                Arguments.of(1, 10, 21)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateWithoutAce")
    void 에이스가_포함되지_않아도_합계가_정상적으로_구해져야_한다(int aceAmount, int currentSum, int expect) {
        Score result = Rank.sumWithAce(aceAmount, new Score(currentSum));
        Assertions.assertEquals(result.value(), expect);
    }

    static Stream<Arguments> calculateWithoutAce() {
        return Stream.of(
                Arguments.of(0, 14, 14),
                Arguments.of(0, 10, 10),
                Arguments.of(0, 9, 9)
        );
    }

}
