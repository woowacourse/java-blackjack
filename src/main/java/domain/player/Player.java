package domain.player;

import domain.card.Card;

public class Player {

    private final Name name;

    private CardStatus cardStatus;

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
}
