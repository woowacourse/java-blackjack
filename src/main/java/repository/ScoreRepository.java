package repository;

import domain.game_result.vo.PlayerWinningInfo;
import domain.game_result.ScoreBoard;
import domain.common.PlayedGameResult;
import domain.game_result.vo.DealerWinningScore;
import java.util.List;
import service.BlackJackCommandService;
import service.BlackJackQueryService;

public class ScoreRepository {

    private ScoreBoard scoreBoard;

    /**
     * Command Side
     * {@link BlackJackCommandService}
     */

    public void setup(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void recordCurrentGameResult(PlayedGameResult currentPlayersResult) {
        scoreBoard.record(currentPlayersResult);
    }

    public void recordDealerGameResult(PlayedGameResult dealerNameAndCardInfos) {
        scoreBoard.record(dealerNameAndCardInfos);
    }

    /**
     * Query Side
     * {@link BlackJackQueryService}
     */

    public List<PlayedGameResult> getPlayerGameResults() {
        return scoreBoard.playerGameResults();
    }

    public DealerWinningScore getDealerWinningScore() {
        return scoreBoard.dealerWinningScore();
    }

    public List<PlayerWinningInfo> getPlayerWinningInfos() {
        return scoreBoard.playerWinningInfos();
    }
}
