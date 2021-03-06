package blackjack.domain.participants;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    private static final int MAX_SUM_FOR_MORE_CARD = 16;

    public Dealer(final String name) {
        super(name);
    }

    @Override
    public boolean isWinner(Participant player) {
        if (player.isBust()) {
            return true;
        }
        return player.calculate() <= this.calculate() && !isBust();
    }

    @Override
    public List<Card> showCards() {
        return Collections.singletonList(getPlayerCards().get(0));
    }

    @Override
    public boolean checkMoreCardAvailable() {
        return (calculate() <= MAX_SUM_FOR_MORE_CARD);
    }

}
