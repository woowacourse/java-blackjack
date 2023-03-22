package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.result.Score;

public abstract class User {

    private final Name name;
    private final CardGroup cardGroup;

    protected User(final Name name, CardGroup cardGroup) {
        this.name = name;
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

    public abstract CardGroup getFirstOpenCardGroup();
}
