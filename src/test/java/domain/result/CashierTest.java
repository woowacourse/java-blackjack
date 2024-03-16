package domain.result;

import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.bet.GamerWallet;
import domain.gamer.bet.GamersWallet;
import domain.gamer.bet.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CashierTest {

    Player player;
    Dealer dealer;
    Money money;
    Cashier cashier;

    @BeforeEach
    void setup() {
        player = new Player("player");
        dealer = new Dealer();
        money = new Money(10000);
        List<GamerWallet> gamerWallets = List.of(new GamerWallet(player, money),
                new GamerWallet(dealer, new Money(0)));
        cashier = new Cashier(new GamersWallet(gamerWallets));
    }

    @DisplayName("승리하면 베팅액의 1배를 준다.")
    @Test
    void testCashierCalculateWinProfit() {
        cashier.calculatePlayersProfit(Map.of(player, WinState.WIN));
        GamersWallet gamersWallet = cashier.getGamersWallet();
        assertThat(gamersWallet.findMoneyByPlayer(player))
                .isEqualTo(10000);
    }

    @DisplayName("패배하면 베팅액의 -1배를 준다.")
    @Test
    void testCashierCalculateLoseProfit() {
        cashier.calculatePlayersProfit(Map.of(player, WinState.LOSE));
        GamersWallet gamersWallet = cashier.getGamersWallet();
        assertThat(gamersWallet.findMoneyByPlayer(player))
                .isEqualTo(-10000);
    }

    @DisplayName("무승부하면 0을 준다.")
    @Test
    void testCashierCalculateDrawProfit() {
        cashier.calculatePlayersProfit(Map.of(player, WinState.DRAW));
        GamersWallet gamersWallet = cashier.getGamersWallet();
        assertThat(gamersWallet.findMoneyByPlayer(player))
                .isEqualTo(0);
    }

    @DisplayName("블랙잭이면 베팅액의 1.5배를 준다.")
    @Test
    void testCashierCalculateBlackJackProfit() {
        cashier.calculatePlayersProfit(Map.of(player, WinState.BLACK_JACK));
        GamersWallet gamersWallet = cashier.getGamersWallet();
        assertThat(gamersWallet.findMoneyByPlayer(player))
                .isEqualTo(15000);
    }

    @DisplayName("딜러의 수익을 계산한다.")
    @Test
    void testCashierCalculateDealerProfit() {
        Player player1 = new Player("player1");
        Money money1 = new Money(10000);
        Player player2 = new Player("player2");
        Money money2 = new Money(20000);

        List<GamerWallet> gamerWallets = List.of(new GamerWallet(player1, money1),
                new GamerWallet(player2, money2),
                new GamerWallet(dealer, new Money(0)));

        Cashier cashier = new Cashier(new GamersWallet(gamerWallets));
        GamersWallet gamersWallet = cashier.getGamersWallet();
        cashier.calculatePlayersProfit(Map.of(player1, WinState.WIN, player2, WinState.DRAW));
        cashier.calculateDealerProfit(dealer);
        assertThat(gamersWallet.findMoneyByPlayer(dealer)).isEqualTo(-10000);
    }
}
