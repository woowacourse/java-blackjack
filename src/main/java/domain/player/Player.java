package domain.player;

import domain.card.Card;
import java.util.List;
import java.util.Map;

public class Player {

    protected final Name name;

    protected CardStatus cardStatus;

    public Player(String name) {
        this.name = new Name(name);
        this.cardStatus = new CardStatus();
    }

    public boolean isEqualName(String name) {
        if(this.name.isEqualName(name)) return true;
        return false;
    }

    public void addCard(Card card) {
        cardStatus.addCard(card);
    }

    public int getCardSize() {
        return cardStatus.getCardsSize();
    }

    public boolean isBust() {
        return cardStatus.isBust();
    }

    public int getTotalValue() {
        return cardStatus.getTotalValue();
    }

    public String getName() {
        return name.getName();
    }

    public List<String> getCardStatus() {
        return cardStatus.getCardsInfo();
    }
}
