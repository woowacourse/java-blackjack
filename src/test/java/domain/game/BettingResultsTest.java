package domain.game;

import domain.participant.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static domain.constant.GameResult.*;
import static org.assertj.core.api.Assertions.assertThat;

class BettingResultsTest {

    @DisplayName("게임 결과에 따른 딜러와 플레이어들의 수익을 계산한다.")
    @Test
    void createBettingResultsTest() {
        // Given
        PlayerName kelly = new PlayerName("kelly");
        PlayerName py = new PlayerName("py");
        PlayerName movin = new PlayerName("movin");
        PlayerName low = new PlayerName("low");
        BettingMoneyStore bettingMoneyStore = new BettingMoneyStore(
                Map.of(
                        kelly, new BettingMoney(10000),
                        py, new BettingMoney(10000),
                        movin, new BettingMoney(20000),
                        low, new BettingMoney(10000)
                ));
        BlackjackGameResults blackjackGameResults = new BlackjackGameResults(
                List.of(LOSE, LOSE, WIN, DRAW),
                Map.of(kelly, BLACKJACK_WIN, py, WIN, movin, LOSE, low, DRAW)
        );

        // When
        BettingResults bettingResults = BettingResults.of(bettingMoneyStore, blackjackGameResults);

        // Then
        assertThat(bettingResults.dealerRevenue()).isEqualTo(-5000);
        assertThat(bettingResults.playerRevenues().get(kelly)).isEqualTo(15000);
        assertThat(bettingResults.playerRevenues().get(py)).isEqualTo(10000);
        assertThat(bettingResults.playerRevenues().get(movin)).isEqualTo(-20000);
        assertThat(bettingResults.playerRevenues().get(low)).isEqualTo(0);
    }
}
