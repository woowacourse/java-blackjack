package blackjack.domain;

public enum Status {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public static Status compare(int dealerScore, int playerScore) {
        if (dealerScore > 21 || (playerScore > dealerScore && playerScore <= 21)) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }

    @Override
    public String toString() {
        return status;
    }
}
