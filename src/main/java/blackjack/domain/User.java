package blackjack.domain;

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

    public Cards showCards(){
        return this.cards;
    }

    public String getName() {
        return this.name.getName();
    }

    public abstract boolean isDrawable();
}
