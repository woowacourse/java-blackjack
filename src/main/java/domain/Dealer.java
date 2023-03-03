package domain;

import java.util.ArrayList;
import java.util.List;


public class Dealer extends Player {

    public Dealer() {
        this(new ArrayList<>());
    }

    public Dealer(List<Card> cards) {
        super("딜러", cards);
    }

    @Override
    public boolean canHit() {
        return getScore() > 16;
    }

    public boolean drawCardIfNecessary(Deck deck) {
        if (!canHit()) {
            addCard(deck.drawCard());
            return true;
        }
        return false;
    }
}


