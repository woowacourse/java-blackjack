package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BattingMoneyTest {
    private BattingMoney battingMoney;

    @Test
    @DisplayName("Money 생성 테스트")
    void construct() {
        assertThat(new BattingMoney(0)).isNotNull();
    }

    @Test
    @DisplayName("Money가 음수일 경우 예외 처리")
    void constructWithNegativeException() {
        assertThatThrownBy(() -> new BattingMoney(-1))
                .isInstanceOf(NegativeMoneyException.class)
                .hasMessage("Money는 음수일 수 없습니다.");
    }

    @Test
    @DisplayName("잘못된 값이 들어올 경우 예외 처리")
    void constructWithWrongStringException() {
        assertThatThrownBy(() -> new BattingMoney("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 값을 입력해주세요.");
    }
}
