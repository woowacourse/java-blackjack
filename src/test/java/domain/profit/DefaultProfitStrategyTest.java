package domain.profit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DefaultProfitStrategyTest {

    @Test
    void 블랙잭_상태로_이긴_경우_베팅_금액의_일점오배_수익을_반환한다_소숫점은_버린다() {
        // given
        final int betAmount = 1001;
        Bet bet = new Bet(betAmount);
        UserBattleResult userBattleResult = UserBattleResult.BLACKJACK_WIN;

        // when
        Profit profit = DefaultProfitStrategy.getInstance().calculateProfit(bet, userBattleResult);

        // then
        Assertions.assertThat(profit.value()).isEqualTo(1501);
    }

    @Test
    void 블랙잭이_아닌_상태로_이긴_경우_베팅_금액만큼의_수익을_반환한다() {
        // given
        final int betAmount = 1001;
        Bet bet = new Bet(betAmount);
        UserBattleResult userBattleResult = UserBattleResult.NORMAL_WIN;

        // when
        Profit profit = DefaultProfitStrategy.getInstance().calculateProfit(bet, userBattleResult);

        // then
        Assertions.assertThat(profit.value()).isEqualTo(1001);
    }

    @Test
    void 패배하는_경우_베팅_금액만큼_마이너스_수익을_반환한다() {
        // given
        final int betAmount = 1001;
        Bet bet = new Bet(betAmount);
        UserBattleResult userBattleResult = UserBattleResult.LOSE;

        // when
        Profit profit = DefaultProfitStrategy.getInstance().calculateProfit(bet, userBattleResult);

        // then
        Assertions.assertThat(profit.value()).isEqualTo(-1001);
    }

    @Test
    void 비기는_경우_0의_수익을_반환한다() {
        // given
        final int betAmount = 1001;
        Bet bet = new Bet(betAmount);
        UserBattleResult userBattleResult = UserBattleResult.DRAW;

        // when
        Profit profit = DefaultProfitStrategy.getInstance().calculateProfit(bet, userBattleResult);

        // then
        Assertions.assertThat(profit.value()).isEqualTo(0);
    }
}