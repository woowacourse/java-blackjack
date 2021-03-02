package blackjack.domain;

import java.util.List;

public abstract class User {

    private List<Card> cards;

    public void receiveCards(List<Card> cards) {
        this.cards = cards;
    }

    public int getCardPoint() {
        return 0;
    }

    public void draw(Card card) {
        cards.add(card);
        //TODO 추가 구현
    }

    abstract String getName();

}
