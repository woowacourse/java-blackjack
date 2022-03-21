package blackjack.domain.entry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import blackjack.domain.entry.vo.BettingMoney;
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
    @DisplayName("배팅 금액이 같은 객체는 동등하다.")
    void equalToBettingMoney() {
        assertThat(new BettingMoney(1000)).isEqualTo(new BettingMoney(1000));
    }

    @Test
    @DisplayName("배팅 비율로 배팅 금액을 반환한다.")
    void profit() {
        BettingMoney bettingMoney = new BettingMoney(1000);

        assertThat(bettingMoney.profit(1.5)).isEqualTo(1500);
    }
}
