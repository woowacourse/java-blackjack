package participant;

import game.Card;
import game.Cards;
import game.Deck;
import strategy.DeckSettingStrategy;

import java.util.Comparator;
import java.util.List;

public class Dealer {

    private static final int DEALER_HIT_THRESHOLD = 16;

    private final Deck deck;
    private final Cards cards;

    public Dealer(DeckSettingStrategy strategy) {
        this.deck = new Deck(strategy);
        this.cards = deck.drawInitialCards();
    }

    public Card openOneCard() {
        return cards.getCards().stream()
                .min(Comparator.comparingInt(card -> card.getNumber().getValue()))
                .orElse(cards.getCards().getFirst());
    }

    public List<Card> openCards() {
        return cards.getCards();
    }

    public boolean shouldDealerHit() {
        return sumCardNumbers() <= DEALER_HIT_THRESHOLD;
    }

    public boolean addOneCard(Card card) {
        return cards.addOneCard(card);
    }

    public Cards drawInitialCards() {
        return deck.drawInitialCards();
    }

    public Card drawCard() {
        return deck.drawOneCard();
    }

    public int sumCardNumbers() {
        return cards.sumCardNumbers();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }
}
