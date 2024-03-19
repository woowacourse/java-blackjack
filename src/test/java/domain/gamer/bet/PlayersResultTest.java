package domain.gamer.bet;

import domain.gamer.Player;
import domain.result.Money;
import domain.result.PlayersResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class PlayersResultTest {

    @DisplayName("모든 플레이어의 보유액을 합한다.")
    @Test
    void sumAllPlayerProfit() {
        List<GamerWallet> gamerWallets = List.of(new GamerWallet(new Player("player1"), new Money(10000)), new GamerWallet(new Player("player2"), new Money(20000)));
        PlayersResult playersResult = new PlayersResult(gamerWallets);
        int profit = playersResult.sumAllPlayerProfit();
        Assertions.assertThat(profit).isEqualTo(30000);
    }
}
