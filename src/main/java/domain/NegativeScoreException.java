package domain;

public class NegativeScoreException extends IllegalArgumentException {
    public NegativeScoreException() {
        super("Score 값은 양수이어야 합니다.");
    }
}
