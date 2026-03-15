package domain.bet;

import static message.ErrorMessage.BETTING_MONEY_MUST_BE_MULTIPLE_OF_100;
import static message.ErrorMessage.BETTING_MONEY_NOT_AVAILABLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("베팅 금액을 생성한다.")
    @Test
    void 배팅_금액_생성() {
        Money money = Money.bet(10_000);

        assertThat(money.amount()).isEqualTo(10_000);
    }

    @DisplayName("플레이어는 양수 이외의 값을 배팅할 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {-10_000, 0})
    void _0_이하_배팅_불가(int amount) {
        assertThatThrownBy(() -> Money.bet(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BETTING_MONEY_NOT_AVAILABLE.getMessage());
    }

    @DisplayName("플레이어는 100원 단위의 배팅이 아닌 값을 배팅할 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {130, 50, 230})
    void _100원_단위가_아닌_금액_배팅_불가(int amount) {
        assertThatThrownBy(() -> Money.bet(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BETTING_MONEY_MUST_BE_MULTIPLE_OF_100.getMessage());
    }
}
