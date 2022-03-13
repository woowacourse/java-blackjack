package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HoldingCard {

    private static final int BUST_STANDARD = 21;
    private static final int ACE_DIFFERENCE = 10;

    private final List<Card> cards;

    public HoldingCard() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean checkBust() {
        return cardSum() > BUST_STANDARD;
    }

    public int cardSum() {
        int sum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();

        if (sum > BUST_STANDARD) {
            sum = adjustSum(sum);
        }
        return sum;
    }

    private int adjustSum(int sum) {
        int aceCount = getAceCount();

        while (sum > BUST_STANDARD && aceCount > 0) {
            sum -= ACE_DIFFERENCE;
            aceCount -= 1;
        }
        return sum;
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(card -> card.getNumber() == CardNumber.ACE.getNumber())
                .count();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
