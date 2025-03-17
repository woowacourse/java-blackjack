package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import model.bettingamount.BettingAmount;
import model.gameresult.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BettingAmountTest {

    @DisplayName("금액을 입력 받아 생성할 수 있다.")
    @Test
    void generate_WithAmountValue() {
        assertThatCode(() -> new BettingAmount(10000)).doesNotThrowAnyException();
    }

    @DisplayName("게임 결과에 맞는 수익을 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "WIN, 100, 100",
            "DRAW, 100, 0",
            "LOSE, 100, -100",
            "BLACKJACK_WIN, 100, 150"
    })
    void return_ProfitByGameResult(GameResult result, int bettingAmount, int expectedProfit) {
        var amount = new BettingAmount(bettingAmount);

        int actualProfit = amount.getProfitByGameResult(result);

        assertThat(actualProfit).isEqualTo(expectedProfit);
    }
}
