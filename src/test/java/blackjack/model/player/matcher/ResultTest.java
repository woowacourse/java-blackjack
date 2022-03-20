package blackjack.model.player.matcher;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    @DisplayName("승리 이익 계산")
    void winProfit() {
        Money money = new Money(new BigDecimal("1000"));
        Result result = Result.win(money);
        assertThat(result.profit()).isEqualTo(money);
    }

    @Test
    @DisplayName("무승부 이익 계산")
    void drawProfit() {
        Money money = new Money(new BigDecimal("1000"));
        Result result = Result.draw(money);
        assertThat(result.profit()).isEqualTo(new Money(BigDecimal.ZERO));
    }

    @Test
    @DisplayName("패배 이익 계산")
    void lossProfit() {
        Money money = new Money(new BigDecimal("1000"));
        Result result = Result.loss(money);
        assertThat(result.profit()).isEqualTo(money.negate());
    }

    @Test
    @DisplayName("블랙잭 이익 계산")
    void blackjackProfit() {
        Money money = new Money(new BigDecimal("1000"));
        Result result = Result.blackjack(money);
        assertThat(result.profit()).isEqualTo(money.multiply(new BigDecimal("1.5")));
    }
}
