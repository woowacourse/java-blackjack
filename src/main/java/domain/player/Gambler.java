package domain.player;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Gambler extends Player {
    public Gambler(String name) {
        super(name);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean canGetMoreCard() {
        return !isBust() && !isBlackJack();
    }

    @Override
    public List<Card> getOpenCards() {
        return new ArrayList<>(cards.getCards());
    }
}
