package blackjack.domain.betting;

import blackjack.domain.cardgame.WinningStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitTest {
    @Test
    void 블랙잭이_아니고_이긴_경우의_수익률은_베팅_금액과_동일하다() {
        Money money = new Money(1000);
        WinningStatus status = WinningStatus.WIN;
        Profit profit = Profit.of(money, status);

        assertThat(profit).isEqualTo(new Profit(1000));
    }
}
