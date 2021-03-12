package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {
    protected final Cards cards;
    protected final Name name;

    protected State state;

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

    public void draw(Deck deck){
        this.cards.combine(deck.popOne());
    }

//    public void draw(Deck deck) {
//        state = state.draw(deck.popOne().oneCard());
//    }

    public boolean isBust() {
        return this.cards.isBust();
    }

    public boolean isBlackjack() {
        return this.cards.isBlackjack();
    }

    public double getProfit(Money money) {
        return this.state.calculateProfit(money);
    }

    public int getScore() {
        return this.cards.calculateScore().getScore();
    }

    public List<Card> getCards(){
        return Collections.unmodifiableList(this.cards.getCards());
    }

    public String getName() {
        return this.name.getName();
    }

    public abstract boolean isHit();
}
