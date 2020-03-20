package blackjack.domain;

import java.util.Collections;
import java.util.List;

public abstract class User {
    protected Cards cards = new Cards();
    protected String name;

    public void receiveDistributedCards(CardDeck cardDeck) {
        cards.receiveDistributedCards(cardDeck);
    }

    public void receiveOneMoreCard(CardDeck cardDeck) {
        cards.receiveOneMoreCard(cardDeck);
    }

    public boolean isBlackJack() {
        return cards.isBlackJackStatus();
    }

    public boolean isBust() {
        return cards.isBustStatus();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public List<Card> getRawCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    public abstract List<Card> getInitialCards();

    public abstract boolean isReceivableOneMoreCard();
}
