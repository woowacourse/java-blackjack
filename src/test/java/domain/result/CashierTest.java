package domain.result;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import domain.gamer.Dealer;
import domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        dealer.hit(Card.valueOf(CardNumber.FIVE, CardShape.HEART));
        List<GamerWallet> gamerWallets = List.of(new GamerWallet(player, money),
                new GamerWallet(dealer, new Money(0)));
        cashier = new Cashier(new PlayersResult(gamerWallets), new Judge());
    }

    @DisplayName("승리하면 베팅액의 1배를 준다.")
    @Test
    void testCashierCalculateWinProfit() {
        player.hit(Card.valueOf(CardNumber.SIX, CardShape.HEART));
        cashier.calculateBetResult(List.of(player), dealer);
        PlayersResult playersResult = cashier.getGamersWallet();
        assertThat(playersResult.findMoneyByPlayer(player))
                .isEqualTo(10000);
    }

    @DisplayName("패배하면 베팅액의 -1배를 준다.")
    @Test
    void testCashierCalculateLoseProfit() {
        player.hit(Card.valueOf(CardNumber.FOUR, CardShape.HEART));
        cashier.calculateBetResult(List.of(player), dealer);
        PlayersResult playersResult = cashier.getGamersWallet();
        assertThat(playersResult.findMoneyByPlayer(player))
                .isEqualTo(-10000);
    }

    @DisplayName("무승부하면 0을 준다.")
    @Test
    void testCashierCalculateDrawProfit() {
        player.hit(Card.valueOf(CardNumber.FIVE, CardShape.HEART));
        cashier.calculateBetResult(List.of(player), dealer);
        PlayersResult playersResult = cashier.getGamersWallet();
        assertThat(playersResult.findMoneyByPlayer(player))
                .isEqualTo(0);
    }

    @DisplayName("블랙잭이면 베팅액의 1.5배를 준다.")
    @Test
    void testCashierCalculateBlackJackProfit() {
        player.hit(Card.valueOf(CardNumber.ACE, CardShape.HEART));
        player.hit(Card.valueOf(CardNumber.KING, CardShape.HEART));
        cashier.calculateBetResult(List.of(player), dealer);
        PlayersResult playersResult = cashier.getGamersWallet();
        assertThat(playersResult.findMoneyByPlayer(player))
                .isEqualTo(15000);
    }

    @DisplayName("딜러의 수익을 계산한다.")
    @Test
    void testCashierCalculateDealerProfit() {
        Player player1 = new Player("player1");
        Money money1 = new Money(10000);
        Player player2 = new Player("player2");
        Money money2 = new Money(20000);

        player1.hit(Card.valueOf(CardNumber.FOUR, CardShape.HEART));
        player2.hit(Card.valueOf(CardNumber.SIX, CardShape.HEART));

        List<GamerWallet> gamerWallets = List.of(new GamerWallet(player1, money1),
                new GamerWallet(player2, money2),
                new GamerWallet(dealer, new Money(0)));
        Cashier cashier = new Cashier(new PlayersResult(gamerWallets), new Judge());

        PlayersResult playersResult = cashier.getGamersWallet();
        cashier.calculateBetResult(List.of(player1, player2), dealer);

        assertThat(playersResult.findMoneyByPlayer(dealer)).isEqualTo(-10000);
    }
}
