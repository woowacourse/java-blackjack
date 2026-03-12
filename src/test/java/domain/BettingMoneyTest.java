package domain;

import domain.constant.Result;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class BettingMoneyTest {

    @ParameterizedTest
    @CsvSource({
            "10000, WIN, 10000",
            "10000, DRAW, 0",
            "10000, LOSE, -10000",
            "10000, BUST, -10000",
            "10000, BLACKJACK, 15000"
    })
    void 결과값에_따라_수익률을_계산하여_수익계산(int betting, Result result, double expected) {
        BettingMoney bettingMoney = new BettingMoney(betting);

        assertThat(bettingMoney.calculateProceeds(result)).isEqualTo(expected);
    }
}
