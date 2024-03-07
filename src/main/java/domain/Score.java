package domain;

import java.util.HashMap;
import java.util.Map;

import static domain.Status.*;


public class Score {

    private final Map<Status, Integer> score;

    public Score() {
        score = new HashMap<>(Map.of(
                WIN, 0,
                TIE, 0,
                LOSE, 0
        ));
    }

    public void increaseScore(Status status) {
        Integer presentScore = score.get(status);
        score.put(status, presentScore + 1);
    }

    public int getWinScore() {
        return score.get(WIN);
    }

    public int getTieScore() {
        return score.get(TIE);
    }

    public int getLoseScore() {
        return score.get(LOSE);
    }
}
