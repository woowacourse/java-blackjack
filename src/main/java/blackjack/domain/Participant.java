package blackjack.domain;

import java.util.ArrayList;

public abstract class Participant {
    protected final Cards cards;
    protected final Name name;

    public Participant(String name){
        this(new Name(name));
    }

    public Participant(Name name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = name;
    }

    public void distribute(Cards cards) {
        this.cards.combine(cards);
    }

    public abstract boolean isDrawable();

    public void draw(){
        this.cards.combine(Deck.popOne());
    }

    public Cards showCards(){
        return this.cards;
    }

    public String getName() {
        return this.name.getName();
    }


}
