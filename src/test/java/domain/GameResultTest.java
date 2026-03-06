package domain;

import domain.participant.Dealer;
import domain.participant.Players;
import dto.DealerResultInfo;
import dto.PlayerResultInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    @Test
    void 플레이어의_게임_결과를_반환하다(){
        Players players = new Players(List.of("pobi"));
        Dealer dealer = new Dealer();
        GameResult gameResult = new GameResult(players, dealer);

        List<PlayerResultInfo> playersResult = gameResult.getPlayersResult();

        assertThat(playersResult).hasSize(1);
        assertThat(playersResult.get(0).name()).isEqualTo("pobi");
        assertThat(playersResult.get(0).winningStatus()).isEqualTo(WinningStatus.TIE);
    }

    @Test
    void 딜러의_게임_결과를_반환하다(){
        Players players = new Players(List.of("pobi"));
        Dealer dealer = new Dealer();

        GameResult gameResult = new GameResult(players, dealer);

        DealerResultInfo dealerResult = gameResult.getDealerResult();

        assertThat(dealerResult.winCount()).isEqualTo(0);
        assertThat(dealerResult.tieCount()).isEqualTo(1);
        assertThat(dealerResult.loseCount()).isEqualTo(0);
    }
}