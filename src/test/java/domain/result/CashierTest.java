package domain.result;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
import domain.player.Dealer;
import domain.player.Player;
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
        dealer.hit(Card.valueOf(CardNumber.FIVE, CardShape.HEART));

        cashier = new Cashier(new PlayerMoney(Map.of(player, money)), new Judge());
    }

    @DisplayName("승리하면 베팅액의 1배를 준다.")
    @Test
    void testCashierCalculateWinProfit() {
        player.hit(Card.valueOf(CardNumber.SIX, CardShape.HEART));
        cashier.calculateBetResult(List.of(player), dealer);
        Map<Player, Money> playersResult = cashier.getPlayersResult();
        assertThat(playersResult.get(player).getMoney()).isEqualTo(10000);
    }

    @DisplayName("패배하면 베팅액의 -1배를 준다.")
    @Test
    void testCashierCalculateLoseProfit() {
        player.hit(Card.valueOf(CardNumber.FOUR, CardShape.HEART));
        cashier.calculateBetResult(List.of(player), dealer);
        Map<Player, Money> playersResult = cashier.getPlayersResult();
        assertThat(playersResult.get(player).getMoney()).isEqualTo(-10000);
    }

    @DisplayName("무승부하면 0을 준다.")
    @Test
    void testCashierCalculateDrawProfit() {
        player.hit(Card.valueOf(CardNumber.FIVE, CardShape.HEART));
        cashier.calculateBetResult(List.of(player), dealer);
        Map<Player, Money> playersResult = cashier.getPlayersResult();
        assertThat(playersResult.get(player).getMoney()).isEqualTo(0);
    }

    @DisplayName("블랙잭이면 베팅액의 1.5배를 준다.")
    @Test
    void testCashierCalculateBlackJackProfit() {
        player.hit(Card.valueOf(CardNumber.ACE, CardShape.HEART));
        player.hit(Card.valueOf(CardNumber.KING, CardShape.HEART));
        cashier.calculateBetResult(List.of(player), dealer);
        Map<Player, Money> playersResult = cashier.getPlayersResult();
        assertThat(playersResult.get(player).getMoney()).isEqualTo(15000);
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

        cashier = new Cashier(new PlayerMoney(Map.of(player1, money1, player2, money2)), new Judge());
        cashier.calculateBetResult(List.of(player1, player2), dealer);
        Money dealerMoney = cashier.calculateDealerProfit();

        assertThat(dealerMoney.getMoney()).isEqualTo(-10000);
    }
}
