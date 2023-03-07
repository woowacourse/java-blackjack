package blackjack.domain.result;

public enum WinningStatus {
    WIN,
    TIE,
    LOSE;

    public WinningStatus opposite() {
        if (this.equals(WIN)) {
            return LOSE;
        }
        if (this.equals(LOSE)) {
            return WIN;
        }
        return TIE;
    }
}
