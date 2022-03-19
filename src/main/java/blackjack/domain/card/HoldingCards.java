package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class HoldingCards {

    private static final int BUST_STANDARD = 21;
    private static final int ACE_DIFFERENCE = 10;

    private final List<Card> cards;

    public HoldingCards() {
        this.cards = new ArrayList<>();
    }

    public HoldingCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cardSum() > BUST_STANDARD;
    }

    public int cardSum() {
        int sum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        if (sum < BUST_STANDARD) {
            sum = adjustSum(sum);
        }
        return sum;
    }

    private int adjustSum(int sum) {

        while (hasAce() && (sum + ACE_DIFFERENCE) <= BUST_STANDARD ) {
            sum += ACE_DIFFERENCE;
        }
        return sum;
    }

    public boolean isBlackJack() {
        return hasAce() && hasNumberTenCard() ;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(card -> card.getCardNumber() == CardNumber.ACE);
    }

    private boolean hasNumberTenCard() {
        return cards.stream()
                .anyMatch(card -> card.getCardNumber() == CardNumber.TEN ||
                        card.getCardNumber() == CardNumber.JACK ||
                        card.getCardNumber() == CardNumber.QUEEN ||
                        card.getCardNumber() == CardNumber.KING);
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getAllCards() {
        return List.copyOf(cards);
    }
}
