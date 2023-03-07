package blackjack.model;

public enum ResultState {
    BLACKJACK(10),
    STAND(5),
    PLAYER_BUST(0),
    DEALER_BUST(1);

    private final int winningScore;

    ResultState(int winningScore) {
        this.winningScore = winningScore;
    }

    public int getWinningScore() {
        return winningScore;
    }
}
