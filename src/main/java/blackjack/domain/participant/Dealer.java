package blackjack.domain.participant;

public class Dealer extends Participant {
    private static final int MAX_SUM_FOR_MORE_CARD = 16;
    private int winCount;

    public Dealer(final String name) {
        super(name);
        this.winCount = 0;
    }

    public boolean checkMoreCardAvailable() {
        return (calculate() <= MAX_SUM_FOR_MORE_CARD);
    }

    public boolean isWinner(final int playerResult) {
        return (playerResult <= calculate() && !isBust());
    }

    public void winSinglePlayer() {
        this.winCount++;
    }

    public int getWinCount() {
        return this.winCount;
    }
}
