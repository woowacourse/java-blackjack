package blackjack.domain.user;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class User {

    protected static final String CARDS_GUIDE_MESSAGE = "카드: ";

    protected final Cards cards;
    protected final Name name;

    public User(String name) {
        this.cards = new Cards();
        this.name = new Name(name);
    }

    abstract public boolean canHit();

    public final void hit(Card card) {
        cards.addCard(card);
    }

    public final String showCards() {
        return name.getName() + CARDS_GUIDE_MESSAGE + cards.loadCards();
    }

    public final String getName() {
        return name.getName();
    }

    public final Score getScore() {
        return cards.calculateTotalScore();
    }
}
