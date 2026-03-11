package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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
                .hasMessage("문자가 아닌 숫자를 입력해주세요.");
    }

    @Test
    public void 숫자_범위_초과_예외() {
        assertThatThrownBy(() -> new BattingMoney("2147483648"))
                .isExactlyInstanceOf(GameException.class)
                .hasMessage("입력 가능한 범위를 초과한 숫자입니다.");
    }

    @Test
    public void 음수_입력_예외() {
        assertThatThrownBy(() -> new BattingMoney("-1000"))
                .isExactlyInstanceOf(GameException.class)
                .hasMessage("돈은 음수일 수 없습니다.");
    }

    @Test
    public void 금액_0_입력_예외() {
        assertThatThrownBy(() -> new BattingMoney("0"))
                .isExactlyInstanceOf(GameException.class)
                .hasMessage("0원 이상을 입력해주세요.");
    }
}
