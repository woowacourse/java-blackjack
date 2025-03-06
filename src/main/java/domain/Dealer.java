package domain;

import domain.card.Card;
import java.util.List;

public class Dealer extends Participant  {

    protected Dealer() {
        super("딜러");
    }

    @Override
    public List<Card> getInitialCards() {
        List<Card> cards = super.getCards();
        return cards.subList(0, 1);
    }

    @Override
    public boolean ableToAddCard() {
        int cardsScore = cards.calculateScore();
        return cardsScore <= 16;
    }
}
