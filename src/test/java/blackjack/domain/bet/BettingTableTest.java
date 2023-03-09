package blackjack.domain.bet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BettingTableTest {

    @Test
    @DisplayName("배팅 금액을 저장한다.")
    void bettingTest() {
        final BettingTable bettingTable = new BettingTable();
        final String name = "test";

        bettingTable.bet(name, new Money(5000));

        assertThat(bettingTable.get(name)).isEqualTo(new Money(5000));
    }

}
