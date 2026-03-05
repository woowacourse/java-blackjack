package domain;

public class Dealer extends Participant {
    @Override
    public boolean checkScoreUnderCriterion() {
        return calculateScore() < 17;
    }
}
