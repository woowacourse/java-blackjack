package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int BLACKJACK_NUMBER = 21;
    private static final int ZERO = 0;
    private static final int ACE_NUMBER_DIFFERENCE = 10;
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int calculateTotalScore() {
        int totalScore = makeTotalScore();
        totalScore = mediateTotalScore(totalScore);
        return totalScore;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && makeTotalScore() == BLACKJACK_NUMBER;
    }

    private int mediateTotalScore(int totalScore) {
        int aceCount = countAce();
        while (totalScore > BLACKJACK_NUMBER && aceCount != ZERO) {
            totalScore -= ACE_NUMBER_DIFFERENCE;
            aceCount--;
        }
        return totalScore;
    }

    private int countAce() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    private int makeTotalScore() {
        return this.cards.stream().
                map(Card::getScore).
                reduce(ZERO, Integer::sum);
    }
}
