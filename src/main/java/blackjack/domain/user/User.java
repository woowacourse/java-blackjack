package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.result.Score;

public abstract class User {

    protected final CardGroup cardGroup;

    protected User(CardGroup cardGroup) {
        this.cardGroup = cardGroup;
    }

    final public Score getScore() {
        return cardGroup.getScore();
    }

    final public void drawCard(final Card card) {
        cardGroup.add(card);
    }

    public abstract Name getName();

    final public CardGroup getCardGroups() {
        return cardGroup;
    }

    public abstract CardGroup getFirstOpenCardGroup();
}
