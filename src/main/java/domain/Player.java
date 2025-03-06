package domain;

import domain.card.Card;
import java.util.List;

public class Player extends Participant {

    protected Player(String name) {
        super(name);
    }

    @Override
    public List<Card> getInitialCards() {
        return super.getCards();
    }

    @Override
    public boolean ableToAddCard() {
        int cardsScore = cards.calculateScore();
        return cardsScore <= 21;
    }
}
