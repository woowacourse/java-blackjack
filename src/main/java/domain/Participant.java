package domain;

public class Participant {

    protected final Score score = new Score();

    public void updateStatus(Status status) {
        score.increaseScore(status);
    }

    public int getWinScore() {
        return score.getWinScore();
    }

    public int getTieScore() {
        return score.getTieScore();
    }

    public int getLoseScore() {
        return score.getLoseScore();
    }
}
