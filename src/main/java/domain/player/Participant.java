package domain.player;

import domain.card.Card;
import domain.player.attribute.CardStatus;
import domain.player.attribute.Name;
import java.util.List;

public class Participant {

    protected final Name name;

    protected CardStatus cardStatus;

    public Participant(String name) {
        this.name = new Name(name);
        this.cardStatus = new CardStatus();
    }

    public void addCard(Card card) {
        cardStatus.addCard(card);
    }

    public boolean isBust() {
        return cardStatus.isBust();
    }

    public int getTotalScore() {
        return cardStatus.getTotalValue();
    }

    public String getName() {
        return name.getName();
    }

    public int getCardSize() {
        return cardStatus.getCardsSize();
    }

    public List<String> getCardStatus() {
        return cardStatus.getCardsInfo();
    }

    public boolean isEqualName(String name) {
        if(this.name.isEqualName(name)) return true;
        return false;
    }
}
