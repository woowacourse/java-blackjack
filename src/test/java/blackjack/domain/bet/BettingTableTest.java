package blackjack.domain.bet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingTableTest {

    @Test
    @DisplayName("배팅 금액을 저장한다.")
    void bettingTest() {
        final BettingTable bettingTable = new BettingTable();
        final String name = "test";

        bettingTable.bet(name, new Money(5000));

        assertThat(bettingTable.get(name)).isEqualTo(new Money(5000));
    }

    @ParameterizedTest(name = "입력금액 : {0}")
    @DisplayName("1000원 이상 100,000원 이하의 범위에서 벗어나면 예외를 발생시킨다")
    @ValueSource(ints = {900, 101_000})
    void moneyRangeExceptionTest(int amount) {
        final BettingTable bettingTable = new BettingTable();
        final String name = "test1";
        final Money money = new Money(amount);

        assertThatThrownBy(() -> bettingTable.bet(name, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BettingTable.OUT_OF_RANGE_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("1000원단위 외의 입력에서 예외를 발생한다")
    void unitAmountExceptionTest() {
        final BettingTable bettingTable = new BettingTable();
        final String name = "test1";
        final Money money = new Money(5100);

        assertThatThrownBy(() -> bettingTable.bet(name, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(BettingTable.UNIT_AMOUNT_EXCEPTION_TEST);
    }

}
