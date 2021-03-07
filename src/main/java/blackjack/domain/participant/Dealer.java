package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Dealer extends Player {
    private static final int MAX_SUM_FOR_MORE_CARD = 16;
    private static final String name = "딜러";
    private int winCount = 0;

    public Dealer() {
        super(name);
    }

    public boolean checkMoreCardAvailable() {
        return (calculate() <= MAX_SUM_FOR_MORE_CARD);
    }

    public boolean isWinner(final int playerResult) {
        return (playerResult <= calculate() && !isBust());
    }

    public void increaseWinCount() {
        this.winCount++;
    }

    public int getWinCount() {
        return winCount;
    }

    public Card firstCard() {
        return this.cards.get(0);
    }
}
