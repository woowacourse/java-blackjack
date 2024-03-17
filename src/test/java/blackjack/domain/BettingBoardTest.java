package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import blackjack.domain.player.Player;
import blackjack.domain.result.BettingBoard;
import blackjack.domain.score.GameResult;
import blackjack.domain.result.Money;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class BettingBoardTest {

    @Test
    @DisplayName("플레이어들이 배팅금액을 설정할 수 있다.")
    void createBettingBoardTest() {
        BettingBoard bettingBoard = new BettingBoard();
        Player player = new Player("loki");
        bettingBoard.bet(player, new Money(1000));
        Money money = bettingBoard.findByPlayer(player);
        assertThat(money).isEqualTo(new Money(1000));
    }

    @TestFactory
    @DisplayName("결과에 따른 돈을 계산할 수 있다.")
    Collection<DynamicTest> calculateMoneyTest() {
        Player player = new Player("loki");
        BettingBoard bettingBoard = new BettingBoard();
        return List.of(dynamicTest("블랙잭으로 이기면", () -> {
                    bettingBoard.bet(player, new Money(1000));
                    Map<Player, GameResult> resultBoard = Map.of(player, GameResult.WIN_BY_BLACK_JACK);
                    bettingBoard.calculateMoney(resultBoard);
                    Money money = bettingBoard.findByPlayer(player);
                    assertThat(money).isEqualTo(new Money(1500));
                }),
                dynamicTest("승리하면", () -> {
                    bettingBoard.bet(player, new Money(1000));
                    Map<Player, GameResult> resultBoard = Map.of(player, GameResult.WIN);
                    bettingBoard.calculateMoney(resultBoard);
                    Money money = bettingBoard.findByPlayer(player);
                    assertThat(money).isEqualTo(new Money(1000));
                }),
                dynamicTest("비기면", () -> {
                    bettingBoard.bet(player, new Money(1000));
                    Map<Player, GameResult> resultBoard = Map.of(player, GameResult.DRAW);
                    bettingBoard.calculateMoney(resultBoard);
                    Money money = bettingBoard.findByPlayer(player);
                    assertThat(money).isEqualTo(new Money(0));
                }),
                dynamicTest("지면", () -> {
                    bettingBoard.bet(player, new Money(1000));
                    Map<Player, GameResult> resultBoard = Map.of(player, GameResult.LOSE);
                    bettingBoard.calculateMoney(resultBoard);
                    Money money = bettingBoard.findByPlayer(player);
                    assertThat(money).isEqualTo(new Money(-1000));
                })
        );
    }

    @Test
    @DisplayName("딜러의 돈을 계산할 수 있다.")
    void calculateDealerMoneyTest() {
        Player player1 = new Player("loki");
        Player player2 = new Player("pedro");
        BettingBoard bettingBoard = new BettingBoard();
        bettingBoard.bet(player1, new Money(1000));
        bettingBoard.bet(player2, new Money(1000));
        Map<Player, GameResult> resultBoard = Map.of(
                player1, GameResult.WIN,
                player2, GameResult.WIN_BY_BLACK_JACK
        );
        bettingBoard.calculateMoney(resultBoard);
        Money dealerMoney = bettingBoard.calculateDealerMoney();
        assertThat(dealerMoney.money()).isEqualTo(-2500);
    }
}