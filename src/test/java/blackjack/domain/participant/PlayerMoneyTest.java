package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PlayerMoneyTest {
    @Test
    @DisplayName("PlayerMoney 객체가 잘 생성되는지 테스트")
    public void PlayerMoneyConstructor() {
        assertThatCode(() -> new PlayerMoney(1000))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("PlayerMoney가 0보다 작다면 에러를 출력한다")
    public void PlayerMoneyValidation() {
        assertThatThrownBy(() -> new PlayerMoney(-10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 0 이상이여야 합니다.");
    }

    @Test
    @DisplayName("수익률에 따라 얻은 돈/잃은 돈을 반환")
    public void resetMoneyByProfit() {
        final PlayerMoney playerMoney = new PlayerMoney(1000);
        double profit = playerMoney.calculateProfit(1.5);
        assertThat(profit).isEqualTo(1500);
    }
}
