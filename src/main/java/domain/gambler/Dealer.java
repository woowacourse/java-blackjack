package domain.gambler;

import domain.Card;
import domain.Cards;
import java.util.Comparator;

public class Dealer extends Gambler {

    public static final int DEALER_HIT_THRESHOLD = 16;

    private final Cards cards;

    public Dealer(Cards cards) {
        super(cards);
        this.cards = cards;
    }

    public Card openOneCard() {
        return cards.getCards().stream()
                .min(Comparator.comparingInt(card -> card.getNumber().getValue()))
                .orElse(cards.getCards().getFirst());
    }

    public boolean isSumUnderSixteen() {
        return sumCardScores() <= DEALER_HIT_THRESHOLD;
    }
}
