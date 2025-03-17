package participant;

import game.Card;
import game.Deck;

import java.util.Comparator;

public class Dealer extends Participant {

    private static final int DEALER_HIT_THRESHOLD = 16;

    public Dealer(Deck deck) {
        super(deck);
    }

    public Card openOneCard() {
        return cards.getCards().stream()
                .min(Comparator.comparingInt(card -> card.getNumber().getScore()))
                .orElse(cards.getCards().getFirst());
    }

    public boolean shouldDealerHit() {
        return sumCardNumbers() <= DEALER_HIT_THRESHOLD;
    }
}
