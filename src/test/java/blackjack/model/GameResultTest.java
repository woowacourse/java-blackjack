package blackjack.model;

import static blackjack.model.Rank.*;
import static blackjack.model.Result.*;
import static blackjack.model.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.player.Gamer;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {

    @Test
    @DisplayName("플레이어가 베팅 금액의 1.5배를 획득하는 경우 테스트")
    void bettingOneAndAHalfTest() {
        GameResult gameResult = new GameResult();
        gameResult.updatePlayerBettingResult(
                new Gamer("player", List.of(new Card(ACE, SPADE), new Card(JACK, HEART)),
                        new Betting(1000)), WIN);
        assertThat(gameResult.getPlayersResult().values()).containsExactly(new Profit(1500));
    }

    @Test
    @DisplayName("비기는 경우 테스트")
    void playerDrawBettingTest() {
        GameResult gameResult = new GameResult();
        gameResult.updatePlayerBettingResult(
                new Gamer("player", List.of(new Card(NINE, SPADE), new Card(JACK, HEART)),
                        new Betting(1000)), DRAW);
        assertThat(gameResult.getPlayersResult().values()).containsExactly(new Profit(0));
    }

    @ParameterizedTest(name = "[{index}] 베팅 금액 1배 테스트")
    @MethodSource("provideWinPlayer")
    @DisplayName("플레이어가 이긴 경우 베팅 금액 1배 획득 테스트")
    void playerWinBettingTest(Gamer gamer, int money) {
        GameResult gameResult = new GameResult();
        gameResult.updatePlayerBettingResult(gamer, WIN);
        assertThat(gameResult.getPlayersResult().values()).containsExactly(new Profit(money));
    }

    private static Stream<Arguments> provideWinPlayer() {
        return Stream.of(
                Arguments.of(new Gamer("player",
                        List.of(new Card(ACE, SPADE), new Card(JACK, HEART), new Card(JACK, DIAMOND)),
                        new Betting(1000)), 1000),
                Arguments.of(new Gamer("player",
                        List.of(new Card(EIGHT, SPADE), new Card(JACK, HEART)),
                        new Betting(30000)), 30000),
                Arguments.of(new Gamer("player",
                        List.of(new Card(ACE, SPADE), new Card(FIVE, HEART), new Card(JACK, DIAMOND)),
                        new Betting(5500)), 5500)
        );
    }

    @ParameterizedTest(name = "[{index}] 베팅 금액 -1배 테스트")
    @MethodSource("provideLosePlayer")
    @DisplayName("플레이어가 진 경우 테스트")
    void playerLoseBettingTest(Gamer gamer, int money) {
        GameResult gameResult = new GameResult();
        gameResult.updatePlayerBettingResult(gamer, LOSE);
        assertThat(gameResult.getPlayersResult().values()).containsExactly(new Profit(money));
    }

    private static Stream<Arguments> provideLosePlayer() {
        return Stream.of(
                Arguments.of(new Gamer("player",
                        List.of(new Card(ACE, SPADE), new Card(ACE, HEART), new Card(JACK, DIAMOND)),
                        new Betting(1000)), -1000),
                Arguments.of(new Gamer("player",
                        List.of(new Card(TWO, SPADE), new Card(JACK, HEART)),
                        new Betting(30000)), -30000),
                Arguments.of(new Gamer("player",
                        List.of(new Card(SEVEN, SPADE), new Card(FIVE, HEART), new Card(JACK, DIAMOND)),
                        new Betting(5500)), -5500)
        );
    }

    @Test
    @DisplayName("딜러 수익 계산 테스트")
    void dealerOutcomeTest() {
        GameResult gameResult = new GameResult();
        gameResult.updatePlayerBettingResult(
                new Gamer("player1", List.of(new Card(ACE, SPADE), new Card(JACK, HEART)),
                        new Betting(20000)), WIN);
        gameResult.updatePlayerBettingResult(
                new Gamer("player2", List.of(new Card(SIX, SPADE), new Card(JACK, HEART)),
                        new Betting(4000)), LOSE);
        gameResult.calculateDealerResult();
        assertThat(gameResult.getDealerResult()).isEqualTo(-26000);
    }
}