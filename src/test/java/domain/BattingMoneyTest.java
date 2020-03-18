package domain;

import domain.result.PlayerResult;
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
    @DisplayName("블랙잭 승에 따른 배팅 수익 계산")
    void calculateEarningBlackJack() {
        battingMoney = new BattingMoney(10000);
        assertThat(battingMoney.calculateEarning(PlayerResult.BLACKJACK_WIN)).isEqualTo(15000);
    }

    @Test
    @DisplayName("승에 따른 배팅 수익 계산")
    void calculateEarningWin() {
        battingMoney = new BattingMoney(10000);
        assertThat(battingMoney.calculateEarning(PlayerResult.WIN)).isEqualTo(10000);
    }

    @Test
    @DisplayName("무에 따른 배팅 수익 계산")
    void calculateEarningDraw() {
        battingMoney = new BattingMoney(10000);
        assertThat(battingMoney.calculateEarning(PlayerResult.DRAW)).isEqualTo(0);
    }

    @Test
    @DisplayName("패에 따른 배팅 수익 계산")
    void calculateEarningLose() {
        battingMoney = new BattingMoney(10000);
        assertThat(battingMoney.calculateEarning(PlayerResult.LOSE)).isEqualTo(-10000);
    }
}
