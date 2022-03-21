package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BettingTest {

    @DisplayName("베팅 금액에 음수를 입력할 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void betting_negative_exception() {
        assertThatThrownBy(() -> new Betting(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액에 음수를 입력할 수 없습니다.");
    }

    @DisplayName("베팅 금액이 10원 단위가 아닐 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void betting_money_unit_exception() {
        assertThatThrownBy(() -> new Betting(1234))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 10원 단위로 입력해주세요.");
    }

    @DisplayName("수익을 계산하는 것을 확인한다.")
    @Test
    void get_earning() {
        Betting betting = new Betting(1000);

        assertThat(betting.getEarning(1.5)).isEqualTo(1500.0);
    }
}
