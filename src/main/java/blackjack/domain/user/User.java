package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

public abstract class User {

    protected static final String CARDS_GUIDE_MESSAGE = "카드: ";

    protected final Cards cards;
    protected final Name name;


    public User(String name) {
        this.cards = new Cards();
        this.name = new Name(name);
    }

    public void hitTwoCards(Deck deck) {
        hit(deck.pop());
        hit(deck.pop());
    }

    public void hit(Card card) {
        cards.addCard(card);
    }

    public String showCards() {
        return name.getName() + CARDS_GUIDE_MESSAGE + cards.loadCards();
    }

    public String getName() {
        return name.getName();
    }

    public int getScore() {
        return cards.calculateTotalScore();
    }
}
