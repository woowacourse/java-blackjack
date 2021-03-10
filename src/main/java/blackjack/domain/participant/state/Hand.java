package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(final Card card) {
        this.cards.add(card);
    }

    public Score score() {
        Score score = sumScores();
        int countOfAce = getCountOfAce();
        for (int i = 0; i < countOfAce; i++) {
            score = score.reduceScoreIfBust();
        }
        return score;
    }

    private Score sumScores() {
        return new Score(this.cards
            .stream()
            .mapToInt(Card::getScore)
            .sum()
        );
    }

    public int getScoreToInt() {
        return score().getValue();
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && score().isBlackjack();
    }

    public boolean isBust() {
        return score().isBust();
    }

    private int getCountOfAce() {
        return (int) this.cards
            .stream()
            .filter(Card::isAce)
            .count();
    }

    public List<Card> getCards() {
        return new ArrayList<>(this.cards);
    }
}
