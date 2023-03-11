package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static domain.RewardCalculator.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RewardCalculatorTest {
    @ParameterizedTest(name = "승리하면 배팅한 금액만큼 돈을 얻는다.")
    @CsvSource({"WIN, 2_000", "WIN, 3_000"})
    void test_calculate_win(Result result, int money) {
        assertThat(calculate(result, new Money(money)))
                .isEqualTo(new Money(money));
    }

    @ParameterizedTest(name = "블랙잭으로 승리하면 배팅한 금액의 1.5배만큼 돈을 얻는다.")
    @CsvSource({"WIN_BY_BLACKJACK, 2_000", "WIN_BY_BLACKJACK, 3_000"})
    void test_calculate_win_by_blackjack(Result result, int money) {
        assertThat(calculate(result, new Money(money)))
                .isEqualTo(new Money((int) (1.5 * money)));
    }

    @ParameterizedTest(name = "무승부면 돈을 잃지도 얻지도 않는다.")
    @CsvSource({"DRAW, 2_000", "DRAW, 3_000"})
    void test_calculate_draw(Result result, int money) {
        assertThat(calculate(result, new Money(money)))
                .isEqualTo(new Money(0));
    }

    @ParameterizedTest(name = "패배하면 배팅한 금액만큼 돈을 잃는다.")
    @CsvSource({"LOSE, 2_000", "LOSE, 3_000"})
    void test_calculate_lose(Result result, int money) {
        assertThat(calculate(result, new Money(money)))
                .isEqualTo(new Money(-1 * money));
    }
}