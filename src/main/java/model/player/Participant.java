package model.player;

import java.util.List;
import model.card.Card;
import model.card.Cards;

public class Participant extends Player {

    public Participant(String name, List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean canReceiveCard() {
        return calculateScore() <= MAXIMUM_SUM;
    }
}
