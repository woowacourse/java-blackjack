import static org.assertj.core.api.Assertions.assertThat;

import domain.player.BettingProfit;
import domain.player.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingProfitTest {

    BettingProfit bettingProfit;

    @BeforeEach
    void setup() {
        Money money = new Money(10000);
        bettingProfit = new BettingProfit(money);
    }

    @Test
    @DisplayName("배팅금만큼 받는다.")
    void win() {
        assertThat(bettingProfit.winProfit()).isEqualTo(10000);
    }

    @Test
    @DisplayName("배팅금의 1.5배 받는다.")
    void blackjackWin() {
        assertThat(bettingProfit.blackjackWinProfit()).isEqualTo(15000);
    }

    @Test
    @DisplayName("배팅금을 환불받는다. 수익==0")
    void refund() {
        assertThat(bettingProfit.refundProfit()).isEqualTo(0);
    }

    @Test
    @DisplayName("배팅금을 모두 잃는다.")
    void lose() {
        assertThat(bettingProfit.loseProfit()).isEqualTo(-10000);
    }
}
