package BlackJack.domain.User;

import BlackJack.domain.Card.CardFactory;
import BlackJack.domain.Card.Cards;

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

    abstract public void addCard(CardFactory cardFactory);

    public int getScore() {
        return cards.calculateScore();
    }

}
