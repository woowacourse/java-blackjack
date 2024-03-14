package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.PlayerName;
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
        PlayerName playerName = new PlayerName("loki");
        bettingBoard.bet(playerName, new Money(1000));
        Money money = bettingBoard.findByPlayerName(playerName);
        assertThat(money).isEqualTo(new Money(1000));
    }

    @ParameterizedTest
    @MethodSource("resultAndMoney")
    @DisplayName("결과에 따른 돈을 계산할 수 있다.")
    void calculateMoneyTest(GameResult gameResult, Money expected) {
        PlayerName playerName = new PlayerName("loki");
        BettingBoard bettingBoard = new BettingBoard();
        bettingBoard.bet(playerName, new Money(1000));
        Map<PlayerName, GameResult> resultBoard = Map.of(playerName, gameResult);
        bettingBoard.calculateMoney(resultBoard);
        Money money = bettingBoard.findByPlayerName(playerName);
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
        PlayerName playerName1 = new PlayerName("loki");
        PlayerName playerName2 = new PlayerName("pedro");
        BettingBoard bettingBoard = new BettingBoard();
        bettingBoard.bet(playerName1, new Money(1000));
        bettingBoard.bet(playerName2, new Money(1000));
        Map<PlayerName, GameResult> resultBoard = Map.of(
                playerName1, GameResult.WIN,
                playerName2, GameResult.WIN_BY_BLACK_JACK
        );
        bettingBoard.calculateMoney(resultBoard);
        Money dealerMoney = bettingBoard.calculateDealerMoney();
        assertThat(dealerMoney.money()).isEqualTo(-2500);
    }
}