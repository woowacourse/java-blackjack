package domain.gamer;

import domain.card.Card;
import domain.card.Cards;
import java.util.List;

public class Player {

    private final Name name;
    private final Cards cards;

    public Player(Name name) {
        cards = new Cards();
        this.name = name;
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public boolean isDrawable() {
        return cards.isOver(cards.countMaxScore());
    }

    public int getMaxGameScore() {
        return cards.countMaxScore();
    }

    public List<String> getCardStatus() {
        return cards.getCardsName();
    }

    public Name getName() {
        return name;
    }
}
