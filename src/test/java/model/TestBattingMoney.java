package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.MoneyErrorCode;
import exception.GameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestBattingMoney {

    @Test
    public void 정상_동작() {
        BattingMoney money = new BattingMoney("1000");

        assertThat(money.get()).isEqualTo(1000);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "1.5", "1 000", "", " "})
    public void 숫자가_아닌_입력_예외(String input) {
        assertThatThrownBy(() -> new BattingMoney(input))
                .isExactlyInstanceOf(GameException.class)
                .satisfies(e -> assertThat(((GameException) e).getErrorCode())
                        .isEqualTo(MoneyErrorCode.MONEY_IS_NOT_NUMBER));
    }

    @Test
    public void 숫자_범위_초과_예외() {
        assertThatThrownBy(() -> new BattingMoney("2147483648"))
                .isExactlyInstanceOf(GameException.class)
                .satisfies(e -> assertThat(((GameException) e).getErrorCode())
                        .isEqualTo(MoneyErrorCode.MONEY_IS_OUT_OF_RANGE));
    }

    @Test
    public void 음수_입력_예외() {
        assertThatThrownBy(() -> new BattingMoney("-1000"))
                .isExactlyInstanceOf(GameException.class)
                .satisfies(e -> assertThat(((GameException) e).getErrorCode())
                        .isEqualTo(MoneyErrorCode.MONEY_IS_NEGATIVE));
    }

    @Test
    public void 금액_0_입력_예외() {
        assertThatThrownBy(() -> new BattingMoney("0"))
                .isExactlyInstanceOf(GameException.class)
                .satisfies(e -> assertThat(((GameException) e).getErrorCode())
                        .isEqualTo(MoneyErrorCode.MONEY_IS_ZERO));
    }
}
