package model.participant;

public class Score {
    private Integer score = 0;

    public Integer getScore() {
        return score;
    }

    public void add(Integer score) {
        this.score += score;
    }
}
