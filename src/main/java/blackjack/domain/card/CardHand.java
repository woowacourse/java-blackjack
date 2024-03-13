package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardHand {
    private static final int BONUS_SCORE_OF_ACE = 10;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public CardHand() {
        this.cards = new ArrayList<>();
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public int sumAllScore() {
        final int totalScore = calculateTotalScore();

        if (hasAce()) {
            return addBonusScoreIfNotBust(totalScore);
        }
        return totalScore;
    }

    private int calculateTotalScore() {
        return cards.stream()
                .map(Card::getDenomination)
                .map(Denomination::getScore)
                .reduce(0, Integer::sum);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int addBonusScoreIfNotBust(final int totalScore) {
        int sumWithBonusScore = totalScore + BONUS_SCORE_OF_ACE;

        if (sumWithBonusScore <= BLACKJACK_SCORE) {
            return sumWithBonusScore;
        }
        return totalScore;
    }
}
