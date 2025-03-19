package blackjack.constant;

public enum MatchResult {

    WIN("승", 1.0),
    LOSE("패", -1.0),
    PUSH("무", 0),
    BLACKJACK_WIN("블랙잭 승", 1.5);

    private final String message;
    private final double payoutMultiplier;

    MatchResult(String message, double payoutMultiplier) {
        this.message = message;
        this.payoutMultiplier = payoutMultiplier;
    }

    public double calculatePayout(double betAmount) {
        return betAmount * payoutMultiplier;
    }

    public String getMessage() {
        return message;
    }

    public double getPayoutMultiplier() {
        return payoutMultiplier;
    }
}
