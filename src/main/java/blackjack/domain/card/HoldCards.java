package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HoldCards {

    private static final int INIT_SIZE = 2;

    private final List<Card> cards;

    public HoldCards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static HoldCards drawTwoCards(Drawable drawable) {
        List<Card> cards = new ArrayList<>();
        cards.add(drawable.draw());
        cards.add(drawable.draw());
        return new HoldCards(cards);
    }

    public int getTotalNumber() {
        return CardNumber.getOptimizeTotalNumber(
            cards.stream()
                .map(Card::getCardNumber)
                .collect(Collectors.toList())
        );
    }

    public void add(Card card) {
        cards.add(card);
    }

    public HoldCards copy() {
        return new HoldCards(cards);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public boolean isInitSize() {
        return cards.size() == INIT_SIZE;
    }
}
