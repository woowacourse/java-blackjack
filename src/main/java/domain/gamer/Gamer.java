package domain.gamer;

import domain.card.Cards;
import java.util.List;

public class Gamer {

    protected final Cards cards;

    public Gamer() {
        this.cards = new Cards();
    }

    public int getMaxGameScore() {
        return cards.countMaxScore();
    }

    public List<String> getCardStatus() {
        return cards.getCardsName();
    }
}
