package BlackJack.domain;

import java.util.List;

public class User {

    private String name;
    private List<Card> cards;

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
}
