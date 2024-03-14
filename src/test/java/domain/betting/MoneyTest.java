package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {1_000, 1_000_000_000})
    @DisplayName("생성 성공: 경계값(1_000, 1_000_000_000)")
    void money_NoException(int money) {
        assertThatCode(
            () -> Money.valueOf(money)
        ).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999, 1_000_000_001})
    @DisplayName("생성 실패: 값 범위 위반")
    void money_Exception_ExceedMax(int money) {
        assertThatThrownBy(() -> Money.valueOf(money))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 1,000원 이상 1,000,000,000원 이하로 베팅해 주세요.");
    }

    @Test
    @DisplayName("생성 실패: 1,000원 단위 위반")
    void money_Exception_NoThousands() {
        assertThatThrownBy(() -> Money.valueOf(1100))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 1,000원 단위로 베팅해 주세요.");
    }

    @Test
    @DisplayName("플레이어가 이긴 경우 1배의 수익 발생")
    void findPlayerProfitWhenPlayerWin() {
        Money money = Money.valueOf(1_000_000_000);
        assertThat(money.findPlayerProfitWhenPlayerWin().toInt()).isEqualTo(1_000_000_000);
    }

    @Test
    @DisplayName("비긴 경우 0의 수익 발생")
    void findPlayerProfitWhenTie() {
        Money money = Money.valueOf(1_000_000_000);
        assertThat(money.findPlayerProfitWhenTie().toInt()).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 진 경우 1배의 손해 발생")
    void findPlayerProfitWhenPlayerLose() {
        Money money = Money.valueOf(1_000_000_000);
        assertThat(money.findPlayerProfitWhenPlayerLose().toInt()).isEqualTo(-1_000_000_000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭인 경우 1.5배의 수익 발생")
    void findPlayerProfitWhenPlayerBlackjack() {
        Money money = Money.valueOf(1_000_000_000);
        assertThat(money.findPlayerProfitWhenPlayerBlackjack().toInt()).isEqualTo(1_500_000_000);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 블랙잭인 경우 1배의 수익 발생")
    void findPlayerProfitWhenBothBlackjack() {
        Money money = Money.valueOf(1_000_000_000);
        assertThat(money.findPlayerProfitWhenBothBlackjack().toInt()).isEqualTo(1_000_000_000);
    }

    @Test
    @DisplayName("돈을 더할 수 있다")
    void add() {
        Money money = Money.valueOf(1000);
        Money addedMoney = money.add(Money.valueOf(1000));
        assertThat(addedMoney.toInt()).isEqualTo(2000);
    }

    @Test
    @DisplayName("돈을 뺄 수 있다")
    void subtract() {
        Money money = Money.valueOf(1000);
        Money subtractedMoney = money.subtract(Money.valueOf(1000));
        assertThat(subtractedMoney.toInt()).isEqualTo(0);
    }
}
