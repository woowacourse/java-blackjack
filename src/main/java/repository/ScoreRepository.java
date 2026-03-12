package repository;

import domain.common.PlayedGameResult;
import domain.result.ScoreBoard;
import domain.result.DealerWinningScore;
import domain.result.PlayerWinningInfo;
import java.util.List;

public class ScoreRepository {

    private ScoreBoard scoreBoard;

    public void setup(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void recordCurrentGameResult(PlayedGameResult currentPlayersResult) {
        this.scoreBoard.record(currentPlayersResult);
    }

    public void recordDealerGameResult(PlayedGameResult dealerNameAndCardInfos) {
        scoreBoard = this.scoreBoard.recordDealerResult(dealerNameAndCardInfos);
    }

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
