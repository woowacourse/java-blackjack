package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {

    private static final int INIT_COUNT = 1;
    private static final int DRAW_THRESHOLD = 17;
    private final List<Card> cards;

    public Dealer() {
        this.cards = new ArrayList<>();
    }

    public void drawCard(Deck deck) {
        cards.add(deck.drawCard());
    }

    public List<Card> showCards() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> showInitCards() {
        return Collections.unmodifiableList(cards.subList(0, INIT_COUNT));
    }

    public boolean isDrawable() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum() < DRAW_THRESHOLD;
    }
}
