package blackjack.domain.result;

import blackjack.domain.betting.Bettings;
import blackjack.domain.gamer.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultsTest {

    @DisplayName("게임 결과가 블랙잭이면 배팅 금액의 1.5배를 수익으로 받는다.")
    @Test
    void blackjackTest() {
        // given
        Player player = new Player("eden");
        Bettings bettings = new Bettings();
        bettings.add(player, 10000);

        // when
        GameResults gameResults = new GameResults();
        gameResults.add(player, GameResult.BLACKJACK);

        int playerProfit = gameResults.calculatePlayerProfit(player, bettings);
        // then
        Assertions.assertThat(playerProfit).isEqualTo(15000);
    }

    @DisplayName("게임결과가 무승부이면 수익이 0이다.")
    @Test
    void drawTest() {
        // given
        Player player = new Player("eden");
        Bettings bettings = new Bettings();
        bettings.add(player, 10000);

        // when
        GameResults gameResults = new GameResults();
        gameResults.add(player, GameResult.DRAW);
        int playerProfit = gameResults.calculatePlayerProfit(player, bettings);

        // then
        Assertions.assertThat(playerProfit).isEqualTo(0);
    }

    @DisplayName("게임결과가 승리이면 수익이 1배이다.")
    @Test
    void winTest() {
        // given
        Player player = new Player("eden");
        Bettings bettings = new Bettings();
        bettings.add(player, 10000);

        // when
        GameResults gameResults = new GameResults();
        gameResults.add(player, GameResult.WIN);
        int playerProfit = gameResults.calculatePlayerProfit(player, bettings);

        // then
        Assertions.assertThat(playerProfit).isEqualTo(10000);
    }

    @DisplayName("게임결과가 패배이면 수익이 -1배이다.")
    @Test
    void loseTest() {
        // given
        Player player = new Player("eden");
        Bettings bettings = new Bettings();
        bettings.add(player, 10000);

        // when
        GameResults gameResults = new GameResults();
        gameResults.add(player, GameResult.LOSE);
        int playerProfit = gameResults.calculatePlayerProfit(player, bettings);

        // then
        Assertions.assertThat(playerProfit).isEqualTo(-10000);
    }

    @DisplayName("딜러의 수익은 '-플레이어_수익' 이다.")
    @Test
    void calculateDealerProfitTest() {
        // given
        Player player1 = new Player("eden");
        Player player2 = new Player("aden");
        Player player3 = new Player("cden");

        Bettings bettings = new Bettings();
        bettings.add(player1, 10000);
        bettings.add(player2, 10000);
        bettings.add(player3, 10000);

        // when
        GameResults gameResults = new GameResults();
        gameResults.add(player1, GameResult.LOSE);
        gameResults.add(player2, GameResult.WIN);
        gameResults.add(player3, GameResult.BLACKJACK);

        int dealerProfit = gameResults.calculateDealerProfit(bettings);

        // then
        Assertions.assertThat(dealerProfit).isEqualTo(-15000);
    }
}
