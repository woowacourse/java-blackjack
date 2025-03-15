package blackjack.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class MoneyTest {

    @Test
    @DisplayName("퍼센트 단위로 입력받은 이익률을 통해서 원금을 제외한 수익을 구할 수 있다")
    void canApplyProfitRate() {
        // given
        int amount = 10000;
        Money money = Money.fromBettingAmount(amount);

        // when
        Money m1 = money.applyProfitRate(100);
        Money m2 = money.applyProfitRate(50);
        Money m3 = money.applyProfitRate(0);
        Money m4 = money.applyProfitRate(-100);

        // then
        assertAll(() -> {
            assertThat(m1).isEqualTo(Money.from(10000));
            assertThat(m2).isEqualTo(Money.from(5000));
            assertThat(m3).isEqualTo(Money.from(0));
            assertThat(m4).isEqualTo(Money.from(-10000));
        });
    }

    @Test
    @DisplayName("필드가 같다면 같은 객체로 취급한다")
    void shouldEqualsWhenValuesAreSame() {
        // given
        int amount = 10000;
        Money money1 = Money.fromBettingAmount(amount);
        Money money2 = Money.fromBettingAmount(amount);

        // when
        // then
        assertThat(money1).isEqualTo(money2);
    }

    @Test
    @DisplayName("배팅 금액은 음수가 될 수 없다")
    void cannotNegativeValueWhenBet() {
        // given
        int amount = -10000;

        // when
        // then
        assertThatThrownBy(() -> Money.fromBettingAmount(amount))
                .hasMessageContaining("금액은 음수가 될 수 없습니다.");
    }

    @Test
    @DisplayName("수익률을 적용한 금액은 음수가 될 수 있다")
    void canNegativeValueWhenApplyProfitLate() {
        // given
        int amount = -10000;

        // when
        // then
        assertThatCode(() -> Money.fromProfitOrLoss(amount))
                .doesNotThrowAnyException();
    }
}
