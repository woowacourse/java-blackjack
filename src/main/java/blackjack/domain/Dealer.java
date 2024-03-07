package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {

    public static final Score NEED_CARD_CRITERION = new Score(17);

    private final Deck deck;

    public Dealer(final String name) {
        super(new PlayerName(name), new Hands(new ArrayList<>()));
        this.deck = new Deck();
    }

    public boolean needMoreCard() {
        return NEED_CARD_CRITERION.isBiggerThan(calculate());
    }

    public Hands getOpenedHands() {
        return hands.getFirstSkippedCards();
    }

    public void shuffleDeck() {
        deck.shuffle();
    }

    public void addStartCard() {
        List<Card> cards = deck.pick(2);
        cards.forEach(this::addCard);
    }

    public List<Card> drawCards(final int count) {
        return deck.pick(count);
    }
}
