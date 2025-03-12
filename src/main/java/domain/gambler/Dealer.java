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

    public Card openInitialCard() {
        return cards.getCards().stream()
                .min(Comparator.comparingInt(Card::getRankScore))
                .orElse(cards.getCards().getFirst());
    }

    public boolean isSumUnderThreshold() {
        return sumCardScores() <= DEALER_HIT_THRESHOLD;
    }
}
