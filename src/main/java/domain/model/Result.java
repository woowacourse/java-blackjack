package domain.model;

import java.util.List;

public class Result {

    private int victory;
    private int draw;
    private int defeat;

    private Result(final int victory, final int draw, final int defeat) {
        this.victory = victory;
        this.draw = draw;
        this.defeat = defeat;
    }

    public static Result decide(final Score score, final Score comparedScore) {
        if (isDraw(score, comparedScore)) {
            return new Result(0, 1, 0);
        }
        if (score.getValue() > comparedScore.getValue()) {
            return new Result(1, 0, 0);
        }
        return new Result(0, 0, 1);
    }

    public static Result decide(final Score score, final List<Score> comparedScores) {
        Result result = new Result(0, 0, 0);
        comparedScores.forEach(comparedScore -> addResult(score, comparedScore, result));
        return result;
    }

    private static void addResult(final Score score, final Score comparedScore, final Result result) {
        if (isDraw(score, comparedScore)) {
            result.addDraw();
            return;
        }
        if (score.getValue() > comparedScore.getValue()) {
            result.addVictory();
            return;
        }
        result.addDefeat();
    }

    private static boolean isDraw(final Score score, final Score comparedScore) {
        return score.isBust() && comparedScore.isBust() || score.getValue() == comparedScore.getValue();
    }

    public void addVictory() {
        victory++;
    }

    public void addDraw() {
        draw++;
    }

    public void addDefeat() {
        defeat++;
    }

    public int getVictory() {
        return victory;
    }

    public int getDraw() {
        return draw;
    }

    public int getDefeat() {
        return defeat;
    }
}
