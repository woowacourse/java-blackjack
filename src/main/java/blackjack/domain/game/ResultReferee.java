package blackjack.domain.game;

import blackjack.domain.user.name.DealerName;
import blackjack.domain.user.name.UserName;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class ResultReferee {

    private final ScoreBoard scoreBoard;
    private final Map<GameResult, Integer> dealerScoreBoard = new EnumMap<>(GameResult.class);

    public ResultReferee(final ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
        initDealerScore();
    }

    private void initDealerScore() {
        Arrays.stream(GameResult.values())
                .forEach(score -> dealerScoreBoard.put(score, 0));
    }

    public GameResult askResultByUserName(final UserName userName) {
        final int playerScore = scoreBoard.get(userName);
        final int dealerScore = scoreBoard.get(new DealerName());

        return declareScore(playerScore, dealerScore);
    }

    private GameResult declareScore(final int playerScore, final int dealerScore) {
        if (playerScore > dealerScore) {
            updateDealerScoreBoard(GameResult.LOSE);
            return GameResult.WIN;
        }

        if (playerScore == dealerScore) {
            updateDealerScoreBoard(GameResult.DRAW);
            return GameResult.DRAW;
        }
        updateDealerScoreBoard(GameResult.WIN);
        return GameResult.LOSE;
    }

    private void updateDealerScoreBoard(final GameResult score) {
        dealerScoreBoard.put(score, dealerScoreBoard.get(score) + 1);
    }

    public Map<GameResult, Integer> getDealerScore() {
        return new EnumMap<>(dealerScoreBoard);
    }
}
