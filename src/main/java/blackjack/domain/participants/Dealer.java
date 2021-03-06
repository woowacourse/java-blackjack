package blackjack.domain.participants;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    private static final int MAX_SUM_FOR_MORE_CARD = 16;
    private int winCount = 0;

    public Dealer(final String name) {
        super(name);
    }

    @Override
    List<Card> pickCards() {
        return Collections.singletonList(getPlayerCards().get(0));
    }

    public boolean checkMoreCardAvailable() {
        return (calculate() <= MAX_SUM_FOR_MORE_CARD);
    }

    public boolean isWinner(final int playerResult) {
        return playerResult <= calculate() && !isBust();
    }

    public void increaseWinCount() {
        winCount++;
    }

    public int getWinCount() {
        return winCount;
    }
}
