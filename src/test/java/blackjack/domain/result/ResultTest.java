package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ResultTest {
    @ParameterizedTest
    @CsvSource(value = {"20,10,WIN", "15,15,DRAW", "10,20,LOSE"})
    @DisplayName("Result Enum의 checkResult에 대한 테스트")
    void findResultTest(final int score, final int opponentScore, final String expectedResult) {
        final Result result = Result.findResult(score, opponentScore);
        assertThat(result).isEqualTo(Result.valueOf(expectedResult));
    }

    @ParameterizedTest
    @CsvSource(value = {"20,10,승,1", "15,15,무,0", "10,20,패,-1"})
    @DisplayName("getter에 대한 테스트")
    void getterTest(final int score, final int opponentScore, final String result, final double profitRate) {
        final Result resultObj = Result.findResult(score, opponentScore);
        assertThat(resultObj.getResult()).isEqualTo(result);
        assertThat(resultObj.getProfitRate()).isEqualTo(profitRate);
    }
}