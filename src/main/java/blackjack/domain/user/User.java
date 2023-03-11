package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.result.Score;

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

    final public void drawCard(final Card card) {
        cardGroup.add(card);
    }

    final public Name getName() {
        return name;
    }

    final public CardGroup getCardGroups() {
        return cardGroup;
    }

    final public boolean isBust() {
        return cardGroup.isBustScore();
    }

    public abstract CardGroup getFirstOpenCardGroup();
}
