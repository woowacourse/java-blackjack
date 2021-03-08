package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

public abstract class User {

    protected static final String CARDS_GUIDE_MESSAGE = "카드: ";
    private static final String DEALER_NAME = "딜러";

    protected final Cards cards;
    protected String name;

    public User() {
        this(DEALER_NAME);
    }

    public User(String name) {
        this.cards = new Cards();
        this.name = name;
    }

    public void hitTwoCards(Deck deck) {
        hit(deck.pop());
        hit(deck.pop());
    }

    public void hit(Card card) {
        cards.addCard(card);
    }

    public String showCards() {
        return name + CARDS_GUIDE_MESSAGE + cards.loadCards();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return cards.calculateTotalScore();
    }
}
