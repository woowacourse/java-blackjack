package blackjack.domain.user;


import blackjack.domain.GameRule;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.user.component.Name;
import blackjack.domain.user.component.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class User implements GameRule {
    private static final String NULL_ERR_MSG = "%s이(가) 없습니다.";
    protected static int INITIAL_CARD_SIZE = 2;
    protected static int ADDITIONAL_CARD_SIZE = 1;

    private Name name;
    protected Cards cards;

    public User(Name name) {
        Objects.requireNonNull(name);
        this.name = name;
        this.cards = new Cards();
    }

    public User(String name) {
        this.name = new Name(name);
        this.cards = new Cards();
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public Point getPoint() {
        return new Point(cards);
    }
    public void drawCard(CardDeck deck) {
        Objects.requireNonNull(deck, String.format(NULL_ERR_MSG, "카드"));
        int receivableCardSize = getReceivableCardSize();
        for (int i = 0; i < receivableCardSize; i++) {
            Card card = deck.getCard();
            cards.add(card);
        }
    }

    @Override
    public int getReceivableCardSize() {
        if (cards.getSize() == 0) {
            return INITIAL_CARD_SIZE;
        }
        if (receivable()) {
            return ADDITIONAL_CARD_SIZE;
        }
        return 0;
    }
}
