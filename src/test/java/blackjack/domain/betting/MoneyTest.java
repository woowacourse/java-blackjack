package blackjack.domain.betting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class MoneyTest {

    @Test
    @DisplayName("돈을 배팅할 수 있다.")
    void bettingMoney() {
        Money money = new Money(10000);
        assertThat(money).isEqualTo(new Money(10000));
    }

    @Test
    @DisplayName("수익금을 차감할 수 있다.")
    void decreaseMoney() {
        Money money = new Money(10000);
        money.lose();
        assertThat(money.profit()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("블랙잭인 경우 수익금은 배팅금의 1.5배가 된다.")
    void increaseWhenBlackjack() {
        Money money = new Money(10000);
        money.winByBlackjack();
        assertThat(money.profit()).isEqualTo(15000);
    }

    @Test
    @DisplayName("돈을 추가할 수 있다.")
    void increaseMoney() {
        Money money = new Money(10000);
        money.win();
        assertThat(money.profit()).isEqualTo(10000);
    }

    @Test
    @DisplayName("돈은 음수일 수 없다.")
    void throwWhenNegative() {
        assertThatThrownBy(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 금액은 음수일 수 없습니다.");
    }

}
