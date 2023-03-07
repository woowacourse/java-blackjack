package blackjack.domain.user;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.Deck;

import java.util.List;

public abstract class User {

    private final Name name;
    private final CardGroup cardGroup;

    protected User(String name, CardGroup cardGroup) {
        this.name = new Name(name);
        this.cardGroup = cardGroup;
    }

    final public Score getScore() {
        return cardGroup.getScore();
    }

    final public void drawCard(final Deck deck) {
        cardGroup.add(deck.draw());
    }

    final public String getName() {
        return name.getValue();
    }

    final public List<Card> getHandholdingCards() {
        return cardGroup.getCards();
    }

    abstract protected List<Card> getInitialHoldingCards();
}
