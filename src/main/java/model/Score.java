package model;

public class Score {
    private Integer score = 0;

    public Integer get() {
        return score;
    }

    public void add(Integer score) {
        this.score += score;
    }
}
