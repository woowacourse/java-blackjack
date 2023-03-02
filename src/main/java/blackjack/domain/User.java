package blackjack.domain;

import java.util.List;

public abstract class User {

    private final String name;
    private final CardGroup cardGroup;

    protected User(String name, CardGroup cardGroup) {
        this.name = name;
        this.cardGroup = cardGroup;
    }


    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cardGroup.getCards();
    }
}
