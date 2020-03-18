package domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MoneyTest {
    @Test
    @DisplayName("BettingMoney 생성")
    void create() {
        assertThat(new Money("1000")).isInstanceOf(Money.class);
    }

    @Test
    @DisplayName("BettingMoney 생성 - 올바르지 않은 값")
    void create_InvalidMoney_ThrowException() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Money("천 원"));
    }

    @Test
    @DisplayName("배팅 금액 증가")
    void addBettingMoney() {
        Money money = new Money("1000");
        money.addMoney(new BigDecimal("5000"));

        assertThat(money.getMoney().toString()).isEqualTo("6000");
    }

    @Test
    @DisplayName("배팅 금액 감소")
    void subtractBettingMoney() {
        Money money = new Money("1000");
        money.subtractMoney(new BigDecimal("5000"));

        assertThat(money.getMoney().toString()).isEqualTo("-4000");
    }
}
