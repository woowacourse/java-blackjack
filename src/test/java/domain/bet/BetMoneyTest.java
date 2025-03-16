package domain.bet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BetMoneyTest {

    @Test
    @DisplayName("1000원 단위의 금액을 받아 BetMoney를 생성할 수 있다.")
    void test1() {
        assertThat(new BetMoney(10000)).isInstanceOf(BetMoney.class);
    }

    @Test
    @DisplayName("들어온 금액이 1000원 단위가 아닐 경우 예외를 던진다.")
    void test2() {
        assertThatThrownBy(() -> new BetMoney(1500)).isInstanceOf(IllegalArgumentException.class);
    }
}