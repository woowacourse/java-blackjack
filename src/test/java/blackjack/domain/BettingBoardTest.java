package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.PlayerName;
import blackjack.domain.result.BettingBoard;
import blackjack.domain.result.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}