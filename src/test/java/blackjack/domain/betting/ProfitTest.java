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

    @Test
    void 진_경우에_수익률은_베팅_금액의_마이너스_값이다() {
        Money money = new Money(1000);
        WinningStatus status = WinningStatus.LOSE;
        Profit profit = Profit.of(money, status);

        assertThat(profit).isEqualTo(new Profit(-1000));
    }

    @Test
    void 비긴_경우에_수익률은_0이다() {
        Money money = new Money(1000);
        WinningStatus status = WinningStatus.PUSH;
        Profit profit = Profit.of(money, status);

        assertThat(profit).isEqualTo(new Profit(0));
    }
}
