package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HoldCards {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int SUM_HIDDEN_ACE = 10;
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

    public int getOptimizeTotalNumber() {
        int totalNumber = getTotalNumber();
        if (isContainAce() && (totalNumber + SUM_HIDDEN_ACE) <= BLACKJACK_NUMBER) {
            return totalNumber + SUM_HIDDEN_ACE;
        }
        return totalNumber;
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

    private int getTotalNumber() {
        return cards.stream()
            .mapToInt(card -> card.getCardNumber().getValue())
            .sum();
    }

    private boolean isContainAce() {
        return cards.stream()
            .anyMatch(card -> card.getCardNumber().isAce());
    }

    public Optional<Card> getFirstCard() {
        return cards.stream().findFirst();
    }
}
