package domain;

import domain.profit.NormalProfitStrategy;
import domain.profit.Profit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class NormalProfitStrategyTest {

    @Test
    void 블랙잭으로_이긴_경우_베팅_금액의_일점오배_수익을_반환한다_소숫점은_버린다() {
        // given
        Bet bet = new Bet(1001);
        BattleResult battleResult = BattleResult.BLACKJACK;

        // when
        Profit profit = NormalProfitStrategy.getInstance().calculateProfit(bet, battleResult);

        // then
        Assertions.assertThat(profit.getProfit()).isEqualTo(1501);
    }

    @Test
    void 블랙잭이_아닌_상태로_이긴_경우_베팅_금액만큼의_수익을_반환한다() {
        // given
        Bet bet = new Bet(1001);
        BattleResult battleResult = BattleResult.NORMAL_WIN;

        // when
        Profit profit = NormalProfitStrategy.getInstance().calculateProfit(bet, battleResult);

        // then
        Assertions.assertThat(profit.getProfit()).isEqualTo(1001);
    }

    @Test
    void 패배하는_경우_베팅_금액의_음수_수익을_반환한다() {
        // given
        Bet bet = new Bet(1001);
        BattleResult battleResult = BattleResult.LOSE;

        // when
        Profit profit = NormalProfitStrategy.getInstance().calculateProfit(bet, battleResult);

        // then
        Assertions.assertThat(profit.getProfit()).isEqualTo(-1001);
    }

    @Test
    void 비기는_경우_베팅_금액의_음수_수익을_반환한다() {
        // given
        Bet bet = new Bet(1001);
        BattleResult battleResult = BattleResult.DRAW;

        // when
        Profit profit = NormalProfitStrategy.getInstance().calculateProfit(bet, battleResult);

        // then
        Assertions.assertThat(profit.getProfit()).isEqualTo(0);
    }
}
