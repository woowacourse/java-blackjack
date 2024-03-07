package domain;

public class Participant {

    protected final Score score = new Score();

    public void updateStatus(Status status) {
        score.increaseScore(status);
    }
}
