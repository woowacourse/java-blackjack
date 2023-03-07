package domain.participant;

import domain.card.Card;
import domain.card.DrawnCards;
import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {

    private static final int BURST_NUMBER = 21;

    public Player(final Name name, final DrawnCards drawnCards) {
        super(name, drawnCards);
    }

    @Override
    public List<Card> openDrawnCards() {
        return new ArrayList<>(drawnCards.getCards());
    }

    @Override
    public boolean isDrawable() {
        return calculateScore() < BURST_NUMBER;
    }

    public boolean isWin(Dealer dealer) {
        if (isBurst()) {
            return false;
        }

        if (dealer.isBurst()) {
            return true;

        }

        return this.calculateScore() > dealer.calculateScore();
    }
}
