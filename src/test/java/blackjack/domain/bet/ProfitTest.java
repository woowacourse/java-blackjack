package blackjack.domain.bet;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;
import blackjack.domain.result.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitTest {

    private Players players;
    private Dealer dealer;
    private Result result;
    private Profit profit;
    private Card aceSpade;
    private Card queenSpade;

    @BeforeEach
    void init() {
        players = new Players("test1, test2");
        dealer = new Dealer();
        result = new Result(players);
        profit = new Profit();
        aceSpade = Card.of(CardNumber.ACE, CardShape.SPADE);
        queenSpade = Card.of(CardNumber.QUEEN, CardShape.SPADE);
    }

    @DisplayName("플레이어가 블랙잭이며 딜러는 블랙잭이 아닐 경우 베팅 금액의 1.5배를 받는 것을 확인한다.")
    @Test
    void calculate_blackjack_win() {
        dealer.dealCards(List.of(queenSpade, queenSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(aceSpade, queenSpade));
            profit.bet(player, () -> new Betting(1000));
        }

        result.isKeepPlaying(dealer);
        profit.calculate(result);

        for (Player player : players.getPlayers()) {
            assertThat(profit.getBetting(player)).isEqualTo(1500);
        }
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭일 경우 수익이 없는 것을 확인한다.")
    @Test
    void calculate_blackjack_tie() {
        dealer.dealCards(List.of(aceSpade, queenSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(aceSpade, queenSpade));
            profit.bet(player, () -> new Betting(1000));
        }

        result.isKeepPlaying(dealer);
        profit.calculate(result);

        for (Player player : players.getPlayers()) {
            assertThat(profit.getBetting(player)).isEqualTo(0);
        }
    }

    @DisplayName("플레이어는 블랙잭이 아니며 딜러는 블랙잭일 때 베팅 금액만큼 잃는 것을 확인한다.")
    @Test
    void calculate_blackjack_lose() {
        dealer.dealCards(List.of(aceSpade, queenSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(queenSpade, queenSpade));
            profit.bet(player, () -> new Betting(1000));
        }

        result.isKeepPlaying(dealer);
        profit.calculate(result);

        for (Player player : players.getPlayers()) {
            assertThat(profit.getBetting(player)).isEqualTo(-1000);
        }
    }

    @DisplayName("플레이어가 버스트일 때 베팅 금액만큼 잃는 것을 확인한다.")
    @Test
    void calculate_player_bust() {
        dealer.dealCards(List.of(queenSpade, queenSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(queenSpade, queenSpade, queenSpade));
            profit.bet(player, () -> new Betting(1000));
        }

        result.isKeepPlaying(dealer);
        result.compete(dealer);
        profit.calculate(result);

        for (Player player : players.getPlayers()) {
            assertThat(profit.getBetting(player)).isEqualTo(-1000);
        }
    }

    @DisplayName("딜러가 버스트일 때 베팅 금액만큼 받는 것을 확인한다.")
    @Test
    void calculate_dealer_bust() {
        dealer.dealCards(List.of(queenSpade, queenSpade, queenSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(queenSpade, queenSpade));
            profit.bet(player, () -> new Betting(1000));
        }

        result.isKeepPlaying(dealer);
        result.compete(dealer);
        profit.calculate(result);

        for (Player player : players.getPlayers()) {
            assertThat(profit.getBetting(player)).isEqualTo(1000);
        }
    }

    @DisplayName("플레이어가 우승일 경우 베팅 금액만큼 받는 것을 확인한다.")
    @Test
    void calculate_win() {
        dealer.dealCards(List.of(aceSpade, aceSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(queenSpade, queenSpade));
            profit.bet(player, () -> new Betting(1000));
        }

        result.isKeepPlaying(dealer);
        result.compete(dealer);
        profit.calculate(result);

        for (Player player : players.getPlayers()) {
            assertThat(profit.getBetting(player)).isEqualTo(1000);
        }
    }

    @DisplayName("플레이어와 딜러의 점수가 동일할 경우 수익이 없는 것을 확인한다.")
    @Test
    void calculate_tie() {
        dealer.dealCards(List.of(queenSpade, queenSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(queenSpade, queenSpade));
            profit.bet(player, () -> new Betting(1000));
        }

        result.isKeepPlaying(dealer);
        result.compete(dealer);
        profit.calculate(result);

        for (Player player : players.getPlayers()) {
            assertThat(profit.getBetting(player)).isEqualTo(0);
        }
    }

    @DisplayName("플레이어가 패배일 경우 베팅 금액만큼 잃는 것을 확인한다.")
    @Test
    void calculate_lose() {
        dealer.dealCards(List.of(queenSpade, queenSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(aceSpade, aceSpade));
            profit.bet(player, () -> new Betting(1000));
        }

        result.isKeepPlaying(dealer);
        result.compete(dealer);
        profit.calculate(result);

        for (Player player : players.getPlayers()) {
            assertThat(profit.getBetting(player)).isEqualTo(-1000);
        }
    }

    @DisplayName("딜러의 수익을 확인한다.")
    @Test
    void total_profit() {
        dealer.dealCards(List.of(queenSpade, queenSpade));
        for (Player player : players.getPlayers()) {
            player.dealCards(List.of(aceSpade, aceSpade));
            profit.bet(player, () -> new Betting(1000));
        }

        result.isKeepPlaying(dealer);
        result.compete(dealer);
        profit.calculate(result);

        assertThat(profit.dealerProfit()).isEqualTo(2000);
    }
}
