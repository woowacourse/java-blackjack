package domain;

import static domain.MatchResult.DRAW;
import static domain.MatchResult.LOSE;
import static domain.MatchResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MatchResultTest {
    @ParameterizedTest
    @DisplayName("결과 산출 테스트")
    @MethodSource("provideSumForCalculateWinner")
    void calculateWinnerTest(int dealerSum, int playerSum, MatchResult matchResult) {
        assertThat(MatchResult.calculateWinner(dealerSum, playerSum)).isEqualTo(matchResult);
    }

    private static Stream<Arguments> provideSumForCalculateWinner() {
        return Stream.of(Arguments.of(
                10,20,WIN,
                20,20,DRAW,
                20,10,LOSE
        ));
    }

}
