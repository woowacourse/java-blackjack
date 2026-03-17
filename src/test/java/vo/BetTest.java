package vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetTest {
    @Test
    void 정상적인_배팅액_생성() {
        Bet bet = new Bet("1000");
        assertThat(bet.getAmount()).isEqualByComparingTo(new BigDecimal("1000"));
    }

    @Test
    void 배팅액이_비어있으면_예외() {
        assertThatThrownBy(() -> new Bet(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 배팅액이_공백이면_예외() {
        assertThatThrownBy(() -> new Bet("   "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "10.5", "-100", "1e3"})
    void 숫자가_아니면_예외(String input) {
        assertThatThrownBy(() -> new Bet(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 배팅액이_0이면_예외() {
        assertThatThrownBy(() -> new Bet("0"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 블랙잭_수익은_배팅액의_1_5배() {
        Bet bet = new Bet("1000");
        BigDecimal profit = GameResult.BLACKJACK.calculateProfit(bet.getAmount());
        assertThat(profit).isEqualByComparingTo(new BigDecimal("1500"));
    }

    @Test
    void 승리_수익은_배팅액과_동일() {
        Bet bet = new Bet("1000");
        BigDecimal profit = GameResult.WIN.calculateProfit(bet.getAmount());
        assertThat(profit).isEqualByComparingTo(new BigDecimal("1000"));
    }

    @Test
    void 패배_수익은_배팅액의_음수() {
        Bet bet = new Bet("1000");
        BigDecimal profit = GameResult.LOSE.calculateProfit(bet.getAmount());
        assertThat(profit).isEqualByComparingTo(new BigDecimal("-1000"));
    }

    @Test
    void 버스트_수익은_배팅액의_음수() {
        Bet bet = new Bet("1000");
        BigDecimal profit = GameResult.BUST.calculateProfit(bet.getAmount());
        assertThat(profit).isEqualByComparingTo(new BigDecimal("-1000"));
    }

    @Test
    void 무승부_수익은_0() {
        Bet bet = new Bet("1000");
        BigDecimal profit = GameResult.PUSH.calculateProfit(bet.getAmount());
        assertThat(profit).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void 딜러_버스트시_유저_수익은_WIN과_동일() {
        Bet bet = new Bet("1000");
        BigDecimal profit = GameResult.WIN.calculateProfit(bet.getAmount());
        assertThat(profit).isEqualByComparingTo(new BigDecimal("1000"));
    }

    @Test
    void 블랙잭_배팅액이_홀수여도_소수점_정밀도_보장() {
        Bet bet = new Bet("1001");
        BigDecimal profit = GameResult.BLACKJACK.calculateProfit(bet.getAmount());
        assertThat(profit).isEqualByComparingTo(new BigDecimal("1501.5"));
    }
}
