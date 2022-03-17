package blackjack.domain.entry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import blackjack.domain.PlayerOutcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, 1_000_000, 4_999_000})
    @DisplayName("배팅 금액이 1000단위로 생성할 수 있다.")
    void createBettingMoney(int amount) {
        assertThatCode(() -> new BettingMoney(amount))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 1001, 1, 100})
    @DisplayName("배팅 금액이 1000단위가 아닌 경우 예외를 발생한다.")
    void throwExceptionBettingMoneyDivide1000(int value) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new BettingMoney(value))
            .withMessage("배팅 금액은 1000단위어야 합니다.");
    }

    @Test
    @DisplayName("배팅 금액이 500만이 넘는 경우 예외를 발생한다.")
    void throwExceptionBettingMoneyOver5_000_000() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new BettingMoney(5_001_000))
            .withMessage("배팅 금액은 500만을 넘을 수 없습니다.");
    }

    @Test
    @DisplayName("블랙잭은 배팅 금액의 1.5배를 반환한다.")
    void countBlackjackBettingMoney() {
        BettingMoney bettingMoney = new BettingMoney(10000);

        assertThat(bettingMoney.countBlackjackPay()).isEqualTo(15000);
    }

    @Test
    @DisplayName("게임에 승리한 경우 배팅한 금액을 받는다.")
    void winBetting() {
        BettingMoney bettingMoney = new BettingMoney(10000);

        assertThat(bettingMoney.bet(PlayerOutcome.WIN)).isEqualTo(10000);
    }

    @Test
    @DisplayName("게임이 진 경우 배팅한 만큼 금액을 잃는다.")
    void loseBetting() {
        BettingMoney bettingMoney = new BettingMoney(10000);

        assertThat(bettingMoney.bet(PlayerOutcome.LOSE)).isEqualTo(-10000);
    }
}
