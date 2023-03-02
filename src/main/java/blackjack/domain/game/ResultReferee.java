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

    public Map<Score, Integer> getDealerScore() {
        return new EnumMap<>(dealerScoreBoard);
    }

    public Score askResultByPlayerName(final UserName name) {

        final int playerScore = scoreBoard.get(name);
        final int dealerScore = scoreBoard.get(new DealerName());

        if (playerScore > dealerScore) {
            return Score.WIN;
        }

        if (playerScore == dealerScore) {
            return Score.DRAW;
        }

        return Score.LOSE;
    }
}
