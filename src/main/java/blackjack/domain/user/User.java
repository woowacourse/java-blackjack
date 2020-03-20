package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;

import java.util.Collections;
import java.util.List;

public abstract class User {
    protected static final int START_INDEX = 0;
    protected Cards cards = new Cards();
    protected String name;

    public void receiveInitialCards(CardDeck cardDeck) {
        cards.addInitialCards(cardDeck);
    }

    public void receiveOneMoreCard(CardDeck cardDeck) {
        cards.addOneMoreCard(cardDeck);
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
