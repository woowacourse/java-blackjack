package domain.game;

import domain.constant.GameResult;
import domain.participant.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BettingMoneyStoreTest {

    @DisplayName("사용자 게임 결과에 따라 수익을 계산해서 반환한다.")
    @MethodSource("playerRevenues")
    @ParameterizedTest
    void getPlayerRevenueTest(final GameResult gameResult, final int expect) {
        // Given
        PlayerName playerName = new PlayerName("kelly");
        BettingMoneyStore bettingMoneyStore = new BettingMoneyStore(Map.of(playerName, new BettingMoney(10000)));

        // When
        int playerRevenue = bettingMoneyStore.getPlayerRevenue(playerName, gameResult);

        // Then
        assertThat(playerRevenue).isEqualTo(expect);
    }

    private static Stream<Arguments> playerRevenues() {
        return Stream.of(
                Arguments.of(GameResult.BLACKJACK_WIN, 15000),
                Arguments.of(GameResult.WIN, 10000),
                Arguments.of(GameResult.LOSE, -10000),
                Arguments.of(GameResult.DRAW, 0)
        );
    }
}
