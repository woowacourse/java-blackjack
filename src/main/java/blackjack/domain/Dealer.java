package blackjack.domain;

public class Dealer extends Player {
    private static final int MAX_SUM_FOR_MORE_CARD = 16;
    private int winCount = 0;

    public Dealer(final String name) {
        super(name);
    }

    public boolean checkMoreCardAvailable() {
        return (calculate() <= MAX_SUM_FOR_MORE_CARD);
    }

    public boolean isWinner(int playerResult) {
        return (playerResult <= calculate() && !isBust());
    }

    public void increaseWinCount() {
        this.winCount++;
    }

    public int getWinCount() {
        return winCount;
    }
}
