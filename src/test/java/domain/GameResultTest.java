package domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    @Test
    void 플레이어의_게임_결과를_반환하다(){
        Player player = new Player("pobi");
        Players players = new Players(List.of(player));
        Dealer dealer = new Dealer();
        GameResult gameResult = new GameResult(players, dealer);

        Map<String, String> playersStatistics = gameResult.getPlayersStatistics();

        assertThat(playersStatistics.get("pobi")).isEqualTo(WinningStatus.TIE.name());
    }

    @Test
    void 딜러의_게임_결과를_반환하다(){
        Player player = new Player("pobi");
        Players players = new Players(List.of(player));
        Dealer dealer = new Dealer();

        GameResult gameResult = new GameResult(players, dealer);

        Map<String, String> dealerStatistics = gameResult.getDealerStatistics();

        assertThat(dealerStatistics.get("딜러")).isEqualTo("1무 ");
    }
}