package domain;

import domain.constant.Result;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class BettingMoneyTest {

    @ParameterizedTest
    @CsvSource({
            "10000, 10000",
            "10000, 0",
            "10000, -10000",
            "10000, -10000",
            "10000, 15000"
    })
    void 결과값에_따라_수익률을_계산하여_수익계산(int betting, double expected) {
        PlayerStatus bettingMoney = new PlayerStatus(betting);

        assertThat(bettingMoney.calculateProceeds()).isEqualTo(expected);
    }
}
