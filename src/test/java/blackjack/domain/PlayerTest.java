package blackjack.domain;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    @Test
    @DisplayName("플레이어가 버스트 상태인지 알려준다")
    void isPlayerBust() {
        Player player = new Player("pobi");
        player.receiveCards(
                List.of(new Card(CardNumber.JACK, Symbol.SPADE),
                        new Card(CardNumber.QUEEN, Symbol.SPADE),
                        new Card(CardNumber.KING, Symbol.SPADE)));

        assertThat(player.isFinished()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("승무패 여부에 따라 수익을 계산한다")
    @MethodSource("gameResultsAndRevenues")
    void calculateRightRevenue(int money, GameResult gameResult, int expectedRevenue) {
        Player player = new Player("dog");
        player.putBettingMoney(money);
        assertThat(player.moneyToExchange(gameResult)).isEqualTo(expectedRevenue);
    }

    private static Stream<Arguments> gameResultsAndRevenues() {
        return Stream.of(
                Arguments.of(10000, GameResult.WIN, 10000),
                Arguments.of(20000, GameResult.LOSE, -20000),
                Arguments.of(30000, GameResult.DRAW, 0));
    }

    @Test
    @DisplayName("블랙잭으로 이기면 수익은 1.5배이다")
    void winWithBlackJack_getRevenueHalfMore() {
        Player player = new Player("dog");
        player.putBettingMoney(10000);
        player.receiveCards(
                List.of(new Card(CardNumber.ACE, Symbol.CLOVER),
                        new Card(CardNumber.JACK, Symbol.DIAMOND)));
        assertThat(player.moneyToExchange(GameResult.WIN)).isEqualTo(15000);
    }

    @Test
    @DisplayName("블랙잭이어도 무승부면 수익은 없다")
    void drawWithBlackJack_noRevenue() {
        Player player = new Player("dog");
        player.putBettingMoney(10000);
        player.receiveCards(
                List.of(new Card(CardNumber.ACE, Symbol.CLOVER),
                        new Card(CardNumber.JACK, Symbol.DIAMOND)));
        assertThat(player.moneyToExchange(GameResult.DRAW)).isEqualTo(0);
    }

}
