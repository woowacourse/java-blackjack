package blackjack.model.player;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    @DisplayName("승리 이익 계산")
    void winProfit() {
        Money money = new Money(new BigDecimal("1000"));
        Result result = Result.WIN;
        assertThat(result.profit(money)).isEqualTo(money);
    }

    @Test
    @DisplayName("무승부 이익 계산")
    void drawProfit() {
        Money money = new Money(new BigDecimal("1000"));
        Result result = Result.DRAW;
        assertThat(result.profit(money)).isEqualTo(new Money(BigDecimal.ZERO));
    }

    @Test
    @DisplayName("패배 이익 계산")
    void lossProfit() {
        Money money = new Money(new BigDecimal("1000"));
        Result result = Result.LOSS;
        assertThat(result.profit(money)).isEqualTo(money.negate());
    }

    @Test
    @DisplayName("블랙잭 이익 계산")
    void blackjackProfit() {
        Money money = new Money(new BigDecimal("1000"));
        Result result = Result.BLACKJACK;
        assertThat(result.profit(money)).isEqualTo(money.multiply(new BigDecimal("1.5")));
    }
}
