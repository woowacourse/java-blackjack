package domain.result;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ResultTest {

    private static Stream<Arguments> ResultHandValueProvider() {
        return Stream.of(
                Arguments.of(Result.WIN,20,19),
        Arguments.of(Result.LOSE, 19, 20)
        );
    }

    private static Stream<Arguments> ResultHandCountProvider() {
        return Stream.of(
                Arguments.of(Result.WIN,2,3),
                Arguments.of(Result.TIE,3,3),
                Arguments.of(Result.LOSE, 3, 2)
        );
    }
    @ParameterizedTest
    @MethodSource("ResultHandValueProvider")
    @DisplayName("player와 dealer의 점수를 비교해 승패를 정한다. 점수가 같을 경우 패에 있는 카드 개수를 통해 추가 계산하기 때문에 점수만으로 동점은 없다.")
    void isHigherPlayerHandValueTest(Result result, int playerHandValue, int dealerHandValue) {
        Assertions.assertThat(Result.isHigherPlayerHandValue(playerHandValue, dealerHandValue)).isEqualTo(result);
    }

    @ParameterizedTest
    @MethodSource("ResultHandCountProvider")
    @DisplayName("player와 dealer의 점수가 동점일 경우, 패에 있는 카드가 더 적은 사람이 승리한다. 같은 경우 무승부이다.")
    void isGreaterPlayerHandCountTest(Result result, int playerHandCount, int dealerHandCount) {
        Assertions.assertThat(Result.isGreaterPlayerHandCount(playerHandCount, dealerHandCount)).isEqualTo(result);
    }
}
