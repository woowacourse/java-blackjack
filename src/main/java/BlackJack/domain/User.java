package BlackJack.domain;

import java.util.List;

public abstract class User {

    protected String name;
    protected List<Card> cards;

    public User(String name){
        this.name = name;
        this.cards = CardFactory.drawTwoCards();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    abstract public void addCard();

}
