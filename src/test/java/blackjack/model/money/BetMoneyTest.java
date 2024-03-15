package blackjack.model.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetMoneyTest {

    @Test
    @DisplayName("전달 받은 금액으로 Money를 생성한다.")
    void createMoney() {
        int money = 1_000;

        assertThat(new BetMoney(money).getBetMoney()).isEqualTo(1_000);
    }

    @ParameterizedTest(name = "[{index}] 베팅 금액이 {0}원이면 예외가 발생한다.")
    @ValueSource(ints = {0, -1, 100_000_001})
    @DisplayName("1원 미만, 1억원 초과 금액을 베팅할 수 없다.")
    void createMoneyByInvalidBetMoneyAmount(int betMoney) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BetMoney(betMoney))
                .withMessage("1원부터 1억원까지만 베팅 가능합니다.");
    }

    @Test
    @DisplayName("현재 금액에 전달 받은 숫자를 곱한 값을 반환한다.")
    void multiply() {
        BetMoney betMoney = new BetMoney(1_000);
        int newBetMoney = betMoney.multiply(1.5);

        assertThat(newBetMoney).isEqualTo(1_500);
    }
}
