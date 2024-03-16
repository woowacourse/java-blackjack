package domain.gamer.bet;

import domain.gamer.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class GamersWalletTest {

    @DisplayName("모든 플레이어의 보유액을 합한다.")
    @Test
    void sumAllPlayerProfit() {
        List<GamerWallet> gamerWallets = List.of(new GamerWallet(new Player("player1"), new Money(10000)), new GamerWallet(new Player("player2"), new Money(20000)));
        GamersWallet gamersWallet = new GamersWallet(gamerWallets);
        int profit = gamersWallet.sumAllPlayerProfit();
        Assertions.assertThat(profit).isEqualTo(30000);
    }
}
