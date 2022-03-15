package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HoldCards {

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

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
