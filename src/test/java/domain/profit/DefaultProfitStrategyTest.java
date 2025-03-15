package domain.profit;

import domain.BattleResult;
import domain.Bet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DefaultProfitStrategyTest {
    
    @Test
    void 블랙잭으로_이긴_경우_베팅_금액의_일점오배_수익을_반환한다_소숫점은_버린다() {
        // given
        final int betAmount = 1001;
        Bet bet = new Bet(betAmount);
        BattleResult battleResult = BattleResult.BLACKJACK;

        // when
        Profit profit = DefaultProfitStrategy.getInstance().calculateProfit(bet, battleResult);

        // then
        Assertions.assertThat(profit.getValue()).isEqualTo(1501);
    }

    @Test
    void 블랙잭이_아닌_상태로_이긴_경우_베팅_금액만큼의_수익을_반환한다() {
        // given
        final int betAmount = 1001;
        Bet bet = new Bet(betAmount);
        BattleResult battleResult = BattleResult.NORMAL_WIN;

        // when
        Profit profit = DefaultProfitStrategy.getInstance().calculateProfit(bet, battleResult);

        // then
        Assertions.assertThat(profit.getValue()).isEqualTo(1001);
    }

    @Test
    void 패배하는_경우_베팅_금액만큼_마이너스_수익을_반환한다() {
        // given
        final int betAmount = 1001;
        Bet bet = new Bet(betAmount);
        BattleResult battleResult = BattleResult.LOSE;

        // when
        Profit profit = DefaultProfitStrategy.getInstance().calculateProfit(bet, battleResult);

        // then
        Assertions.assertThat(profit.getValue()).isEqualTo(-1001);
    }

    @Test
    void 비기는_경우_0의_수익을_반환한다() {
        // given
        final int betAmount = 1001;
        Bet bet = new Bet(betAmount);
        BattleResult battleResult = BattleResult.DRAW;

        // when
        Profit profit = DefaultProfitStrategy.getInstance().calculateProfit(bet, battleResult);

        // then
        Assertions.assertThat(profit.getValue()).isEqualTo(0);
    }
}