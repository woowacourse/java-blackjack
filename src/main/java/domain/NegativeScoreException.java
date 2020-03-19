package domain;

public class NegativeScoreException extends IllegalArgumentException {
    public NegativeScoreException(int input) {
        super(String.format("Score 값은 양수이어야 합니다. (입력 : %d)", input));
    }
}
