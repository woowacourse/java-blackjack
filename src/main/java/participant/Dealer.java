package participant;

import game.Card;
import game.Cards;
import game.Deck;

import java.util.Comparator;
import java.util.List;

public class Dealer {

    private static final int DEALER_HIT_THRESHOLD = 16;

    private final Cards cards;

    public Dealer(Deck deck) {
        this.cards = deck.drawInitialCards();
    }

    public Card openOneCard() {
        return cards.getCards().stream()
                .min(Comparator.comparingInt(card -> card.getNumber().getScore()))
                .orElse(cards.getCards().getFirst());
    }

    public boolean shouldDealerHit() {
        return cards.sumCardNumbers() <= DEALER_HIT_THRESHOLD;
    }

    public boolean addOneCard(Card card) {
        return cards.addOneCard(card);
    }

    public List<Card> openCards() {
        return cards.getCards();
    }

    public int sumCardNumbers() {
        return cards.sumCardNumbers();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }
}
