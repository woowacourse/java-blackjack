package domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @ParameterizedTest()
    @CsvSource(value = {"1,LOSE", "2,DRAW", "3,WIN"})
    @DisplayName("두 결과를 비교하여 승패를 판단한다.")
    void compareTest(int current, Result result) {
        int opponentValue = 2;

        assertThat(Result.compare(current, opponentValue)).isEqualTo(result);
    }

    @ParameterizedTest()
    @CsvSource(value = {"true,false,BLACKJACK", "true,true,DRAW", "false,true,LOSE"})
    @DisplayName("블랙잭이 발생한 경우를 판단한다.")
    void compareBlackjackTest(boolean current, boolean opponent, Result result) {
        assertThat(Result.blackjackCompare(current, opponent)).isEqualTo(result);
    }

    @ParameterizedTest()
    @CsvSource(value = {"true,false,LOSE", "true,true,LOSE", "false,true,WIN"})
    @DisplayName("버스트가 발생한 경우를 판단한다.")
    void compareBustTest(boolean current, boolean opponent, Result result) {
        assertThat(Result.bustCompare(current, opponent)).isEqualTo(result);
    }
}
