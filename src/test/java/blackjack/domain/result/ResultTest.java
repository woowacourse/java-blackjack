package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 리턴 확인 : 플레이어 우승 경우")
    @Test
    void name() {
        Result result = Result.getResult(20, 12);

        assertThat(result).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 리턴 확인 : 플레이어 패 경우")
    @Test
    void name2() {
        Result result = Result.getResult(10, 12);

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 리턴 확인 : 플레이어 무승부 경우")
    @Test
    void name3() {
        Result result = Result.getResult(20, 20);

        assertThat(result).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 리턴 확인 : 플레이어 패 경우 - 플레이어가 21을 초과하고, 딜러는 21을 초과하지 않을 때")
    @Test
    void name4() {
        Result result = Result.getResult(22, 20);

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 리턴 확인 : 플레이어 패 경우 - 플레이어와 딜러 모두 21을 초과할 때")
    @Test
    void name5() {
        Result result = Result.getResult(20, 12);

        assertThat(result).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어와 딜러의 카드 합으로 결과 리턴 확인 : 플레이어 승 경우 - 딜러가 21을 초과하고, 플레이어는 21초과하지 않을 때")
    @Test
    void name6() {
        Result result = Result.getResult(20, 12);

        assertThat(result).isEqualTo(Result.WIN);
    }

    @DisplayName("승,패,무승부 횟수 확인")
    @ParameterizedTest
    @CsvSource(value = {"WIN,2", "DRAW,1", "LOSE,1"})
    void name(Result result, int expected) {
        List<Result> results = Arrays.asList(Result.WIN, Result.WIN, Result.DRAW, Result.LOSE);

        int actual = Result.getSum(result, results);

        assertThat(actual).isEqualTo(expected);
    }
}
