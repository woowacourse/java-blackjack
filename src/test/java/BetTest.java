import static org.assertj.core.api.Assertions.assertThat;

import domain.player.Bet;
import domain.player.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BetTest {

    Bet bet;

    @BeforeEach
    void setup() {
        Money money = new Money(10000);
        bet = new Bet(money);
    }

    @Test
    @DisplayName("배팅금만큼 받는다.")
    void win() {
        assertThat(bet.winProfit()).isEqualTo(10000);
    }

    @Test
    @DisplayName("배팅금의 1.5배 받는다.")
    void blackjackWin() {
        assertThat(bet.blackjackWinProfit()).isEqualTo(15000);
    }

    @Test
    @DisplayName("배팅금을 환불받는다. 수익==0")
    void refund() {
        assertThat(bet.refundProfit()).isEqualTo(0);
    }

    @Test
    @DisplayName("배팅금을 모두 잃는다.")
    void lose() {
        assertThat(bet.loseProfit()).isEqualTo(-10000);
    }
}
