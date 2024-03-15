package blackjack.domain.card;

import blackjack.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardHand {
    private final List<Card> cards;

    public CardHand() {
        this.cards = new ArrayList<>();
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public Score sumAllScore() {
        final Score totalScore = calculateTotalScore();

        if (hasAce()) {
            return totalScore.addBonusScoreIfNotBust();
        }
        return totalScore;
    }

    private Score calculateTotalScore() {
        return cards.stream()
                .map(Card::getDenomination)
                .map(Denomination::getScore)
                .reduce(Score.initialScore(), Score::sum);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        final int cardSize = countCards();
        final Score score = sumAllScore();

        return cardSize == Constants.INITIAL_CARD_COUNT && score.equals(Score.blackjackScore());
    }

    public boolean isBust() {
        final Score score = sumAllScore();
        return score.isGreaterThan(Score.blackjackScore());
    }

    public int countCards() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
