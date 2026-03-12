package domain;

import domain.participant.Dealer;
import domain.participant.Players;
import dto.DealerResultInfo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    @Test
    void 플레이어의_게임_결과를_반환하다() {
        Players players = new Players(List.of("pobi"));
        Dealer dealer = new Dealer();
        GameResult gameResult = new GameResult(players, dealer);

        Map<String, WinningStatus> playerWinningStatus = gameResult.getPlayerWinningStatus();

        assertThat(playerWinningStatus).hasSize(1);
        assertThat(playerWinningStatus).containsEntry("pobi", WinningStatus.TIE);
        assertThat(playerWinningStatus.get("pobi")).isEqualTo(WinningStatus.TIE);
    }

    @Test
    void 딜러의_게임_결과를_반환하다() {
        Players players = new Players(List.of("pobi"));
        Dealer dealer = new Dealer();

        GameResult gameResult = new GameResult(players, dealer);

        DealerResultInfo dealerResultInfo = new DealerResultInfo(
                gameResult.dealerWinCount(),
                gameResult.dealerTieCount(),
                gameResult.dealerLoseCount()
        );

        assertThat(dealerResultInfo.winCount()).isEqualTo(0);
        assertThat(dealerResultInfo.tieCount()).isEqualTo(1);
        assertThat(dealerResultInfo.loseCount()).isEqualTo(0);
    }
}
