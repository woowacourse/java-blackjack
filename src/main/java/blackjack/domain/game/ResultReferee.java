package blackjack.domain.game;

import blackjack.domain.user.name.DealerName;
import blackjack.domain.user.name.UserName;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class ResultReferee {

    private final ScoreBoard scoreBoard;
    private final Map<Score, Integer> dealerScoreBoard = new EnumMap<>(Score.class);

    public ResultReferee(final ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
        initDealerScore();
    }

    private void initDealerScore() {
        Arrays.stream(Score.values())
                .forEach(score -> dealerScoreBoard.put(score, 0));
    }

    public Score askResultByUserName(final UserName userName) {
        final int playerScore = scoreBoard.get(userName);
        final int dealerScore = scoreBoard.get(new DealerName());

        return declareScore(playerScore, dealerScore);
    }

    private Score declareScore(final int playerScore, final int dealerScore) {
        if (playerScore > dealerScore) {
            updateDealerScoreBoard(Score.LOSE);
            return Score.WIN;
        }

        if (playerScore == dealerScore) {
            updateDealerScoreBoard(Score.DRAW);
            return Score.DRAW;
        }
        updateDealerScoreBoard(Score.WIN);
        return Score.LOSE;
    }

    private void updateDealerScoreBoard(final Score score) {
        dealerScoreBoard.put(score, dealerScoreBoard.get(score) + 1);
    }

    public Map<Score, Integer> getDealerScore() {
        return new EnumMap<>(dealerScoreBoard);
    }
}
