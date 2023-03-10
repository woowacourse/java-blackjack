package blackjack.domain;

import blackjack.domain.game.Result;
import blackjack.domain.user.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class SettlementTest {

    @Test
    @DisplayName("승리한 유저를 정산기에 전달한다면 수익금을 베팅금 만큼 반환한다.")
    void winProfitTest() {
        final HashMap<Name, Stake> nameStake = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameStake.put(pooh, new Stake(1000));

        final Settlement settlement = new Settlement(nameStake);
        assertThat(settlement.getProfit(pooh, Result.WIN)).isEqualTo(1000);
    }

    @Test
    @DisplayName("블랙잭 승리한 유저를 정산기에 전달한다면 수익금을 베팅금 1.5배 만큼 반환한다.")
    void blackJackWinProfitTest() {
        final HashMap<Name, Stake> nameStake = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameStake.put(pooh, new Stake(1000));

        final Settlement settlement = new Settlement(nameStake);
        assertThat(settlement.getProfit(pooh, Result.BLACK_JACK_WIN)).isEqualTo(1500);
    }

    @Test
    @DisplayName("비긴 유저를 정산기에 전달한다면 수익금은 없는 것이다.")
    void drawProfitTest() {
        final HashMap<Name, Stake> nameStake = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameStake.put(pooh, new Stake(1000));

        final Settlement settlement = new Settlement(nameStake);
        assertThat(settlement.getProfit(pooh, Result.DRAW)).isEqualTo(0);
    }

    @Test
    @DisplayName("패배한 유저를 정산기에 전달한다면 잃은 돈은 베팅금 만큼이다.")
    void loseProfitTest() {
        final HashMap<Name, Stake> nameStake = new HashMap<>();
        final Name pooh = new Name("푸우");
        nameStake.put(pooh, new Stake(1000));

        final Settlement settlement = new Settlement(nameStake);
        assertThat(settlement.getProfit(pooh, Result.LOSE)).isEqualTo(-1000);
    }
}
