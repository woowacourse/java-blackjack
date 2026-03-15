package domain;

import domain.constant.Result;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerStatusTest {

    @Test
    void naturalBlackJack_상태를_true로_변경한다() {
        PlayerStatus status = new PlayerStatus(1000);

        status.markNaturalBlackJack();

        assertThat(status.isNaturalBlackJack()).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "10000, WIN, 10000",
            "10000, DRAW, 0",
            "10000, LOSE, -10000",
            "10000, BUST, -10000",
            "10000, BLACKJACK, 15000"
    })
    void 결과값에_따라_정산_금액을_계산한다(int bettingMoney, Result result, double expected) {
        PlayerStatus status = new PlayerStatus(bettingMoney);

        double proceeds = status.calculateProceeds(result);

        assertThat(proceeds).isEqualTo(expected);
    }
}