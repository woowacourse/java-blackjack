package blackjack.domain;

import java.util.List;

public abstract class User {

    private final Name name;
    private final CardGroup cardGroup;

    protected User(String name, CardGroup cardGroup) {
        this.name = new Name(name);
        this.cardGroup = cardGroup;
    }

    final public int getScore() {
        return cardGroup.getTotalValue();
    }

    final public void drawCard(final Deck deck){
        cardGroup.add(deck.draw());
    }

    final public String getName() {
        return name.getValue();
    }

    final public List<Card> getStatus() {
        return cardGroup.getCards();
    }

    abstract protected List<Card> getInitialStatus();
}
