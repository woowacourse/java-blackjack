package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class User {

    private static final String DEALER_NAME = "딜러";

    protected final Cards cards;
    protected String name;

    public User(){
        this(DEALER_NAME);
    }

    public User(String name){
        this.cards = new Cards();
        this.name = name;
    }

    public void hit(Card card) {
        cards.addCard(card);
    }

    public String getCards() {
        return cards.getCards();
    }

    public String getName() {
        return name;
    }

    int getScore() {
        return cards.getScore();
    }
}
