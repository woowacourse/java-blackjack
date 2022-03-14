package BlackJack.domain.User;

import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.Card.Cards;

import static BlackJack.domain.Card.Cards.BUST_LINE;

public abstract class User {

    protected String name;
    protected Cards cards;

    public User(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public boolean isBust(){
        return cards.calculateScore() > BUST_LINE;
    }
    abstract public void addCard(CardFactory cardFactory);



}
