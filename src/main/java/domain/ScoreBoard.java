package domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreBoard {

    private static final Name dealer = new Name("dealer");

    private final Map<Name, Score> scores;

    private ScoreBoard(Map<Name, Score> scores) {
        this.scores = scores;
    }

    public static ScoreBoard from(List<Name> names) {
        Map<Name, Score> scoreBoard = names.stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> new Score()
                ));
        scoreBoard.put(dealer, new Score());
        return new ScoreBoard(scoreBoard);
    }

    public void updatePlayerScore(Name name, Status status) {
        Score score = scores.get(name);
        score.increaseScore(status);
    }

    public void updateDealerScore(Status status) {
        Score score = scores.get(dealer);
        score.increaseScore(status);
    }

    public Score getPlayerScore(Name name) {
        return scores.get(name);
    }

    public Score getDealerScore() {
        return scores.get(dealer);
    }
}
