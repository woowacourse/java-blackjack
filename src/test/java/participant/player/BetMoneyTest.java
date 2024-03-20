package participant.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetMoneyTest {

    private static final int MIN_BET_MONEY = 3000;
    private static final int MAX_BET_MONEY = 400000;

    @ParameterizedTest
    @ValueSource(ints = {10, 300, 5000000, 600000})
    @DisplayName("베팅이 가능한 최소금액 혹은 최대금액 범위가 아닐 경우 베팅을 하지 못한다.")
    void isCanBettingMoney(int money) {
        Assertions.assertThatThrownBy(() -> new BetMoney(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 " + MIN_BET_MONEY + "원 에서 " + MAX_BET_MONEY + "까지만 가능합니다.");
    }

    @DisplayName("퍼센트에 따른 베팅금액을 출력한다")
    @Test
    void getBetMoneyResult() {
        int money = 3000;
        BetMoney betMoney = new BetMoney(money);

        Assertions.assertThat(betMoney.betMoneyResult(1.5)).isEqualTo(4500);
    }
}
