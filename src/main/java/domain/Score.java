package domain;

public class Score {
    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public boolean isBust(){
        return value > 21;
    }

    public int getValue() {
        return value;
    }
}
