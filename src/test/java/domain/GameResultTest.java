package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.judgement.BettingMoney;
import blackjack.domain.judgement.GameResult;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    @Test
    @DisplayName("승리 시, 배팅 금액 1배 반환")
    void 승리_처리_테스트() {
        GameResult gameResult = GameResult.WIN;
        BettingMoney bettingMoney = new BettingMoney("10000");
        BigDecimal expected = new BigDecimal("10000.0");

        BigDecimal actual = gameResult.calculateProfit(bettingMoney);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("패비 시, 배팅 금액 1배 반납")
    void 패배_처리_테스트() {
        GameResult gameResult = GameResult.LOSE;
        BettingMoney bettingMoney = new BettingMoney("10000");
        BigDecimal expected = new BigDecimal("-10000.0");

        BigDecimal actual = gameResult.calculateProfit(bettingMoney);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("블랙잭 시, 배팅 금액 1.5배 반환")
    void 블랙잭_처리_테스트() {
        GameResult gameResult = GameResult.BLACKJACK;
        BettingMoney bettingMoney = new BettingMoney("10000");
        BigDecimal expected = new BigDecimal("15000.0");

        BigDecimal actual = gameResult.calculateProfit(bettingMoney);

        assertThat(actual).isEqualTo(expected);
    }
}
