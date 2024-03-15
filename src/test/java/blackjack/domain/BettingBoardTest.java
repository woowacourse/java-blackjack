package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.Player;
import blackjack.domain.result.BettingBoard;
import blackjack.domain.score.GameResult;
import blackjack.domain.result.Money;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

    @ParameterizedTest
    @MethodSource("resultAndMoney")
    @DisplayName("결과에 따른 돈을 계산할 수 있다.")
    void calculateMoneyTest(GameResult gameResult, Money expected) {
        Player player = new Player("loki");
        BettingBoard bettingBoard = new BettingBoard();
        bettingBoard.bet(player, new Money(1000));
        Map<Player, GameResult> resultBoard = Map.of(player, gameResult);
        bettingBoard.calculateMoney(resultBoard);
        Money money = bettingBoard.findByPlayer(player);
        assertThat(money).isEqualTo(expected);
    }

    private static Stream<Arguments> resultAndMoney() {
        return Stream.of(
                Arguments.arguments(
                        GameResult.WIN_BY_BLACK_JACK, new Money(1500)
                ),
                Arguments.arguments(
                        GameResult.WIN, new Money(1000)
                ),
                Arguments.arguments(
                        GameResult.DRAW, new Money(0)
                ),
                Arguments.arguments(
                        GameResult.LOSE, new Money(-1000)
                )
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