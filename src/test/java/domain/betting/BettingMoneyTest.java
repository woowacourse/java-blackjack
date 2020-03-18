package domain.betting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class BettingMoneyTest {
    @Test
    @DisplayName("BettingMoney 생성")
    void create() {
        assertThat(new BettingMoney("1000")).isInstanceOf(BettingMoney.class);
    }

    @Test
    @DisplayName("BettingMoney 생성 - 올바르지 않은 값")
    void create_InvalidMoney_ThrowException() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new BettingMoney("천 원"));
    }

    @Test
    @DisplayName("배팅 금액 증가")
    void addBettingMoney() {
        BettingMoney bettingMoney = new BettingMoney("1000");
        bettingMoney.addBettingMoney(new BigDecimal("5000"));

        assertThat(bettingMoney.getBettingMoney().toString()).isEqualTo("6000");
    }

    @Test
    @DisplayName("배팅 금액 감소")
    void subtractBettingMoney() {
        BettingMoney bettingMoney = new BettingMoney("1000");
        bettingMoney.subtractBettingMoney(new BigDecimal("5000"));

        assertThat(bettingMoney.getBettingMoney().toString()).isEqualTo("-4000");
    }
}
