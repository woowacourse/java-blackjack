package blackjack.domain.user;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

import java.util.ArrayList;

public abstract class User {
    protected final Cards cards;
    protected final Name name;

    public User(String name){
        this(new Name(name));
    }

    public User(Name name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = name;
    }

    public void distribute(Cards cards) {
        this.cards.combine(cards);
    }

    public void draw(){
        this.cards.combine(Deck.popOne());
    }

    public boolean isBust() {
        return this.cards.isBust();
    }

    public Cards getCards(){
        return this.cards;
    }

    public String getName() {
        return this.name.getName();
    }

    public abstract boolean isHit();
}
