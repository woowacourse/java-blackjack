package blackjack.domain.state;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hit implements State {
    private List<Card> cards;

    public Hit(ArrayList<Card> initialCard) {
        cards = initialCard;
    }

    @Override
    public boolean isEndState() {
        return false;
    }

    @Override
    public double profit() {
        return 0;
    }

    @Override
    public State changeState() {
        return null;
    }
}
