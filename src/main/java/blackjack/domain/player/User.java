package blackjack.domain.player;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

    protected Hand hand;
    protected Name name;

    public User(Name name) {
        this.hand = new Hand(new ArrayList<>());
        this.name = name;
    }

    public void addCard(Card card){
        List<Card> cards = this.hand.getHand();
        cards.add(card);
        this.hand = new Hand(cards);
    }

    public List<Card> getHand() {
        return this.hand.getHand();
    }

    public String getUserName() {
        return name.getName();
    }

    public abstract boolean isUnderLimit();
}
