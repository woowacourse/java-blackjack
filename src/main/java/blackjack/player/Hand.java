package blackjack.player;

import blackjack.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int INIT_CARD_COUNT = 2;

    private final List<Card> cards;
    private Score score;

    public Hand(List<Card> cards) {
        this.cards = cards;
        updateScore();
    }

    Hand() {
        this(new ArrayList<>());
    }

    public void addCard(Card card) {
        cards.add(card);
        updateScore();
    }

    private void updateScore() {
        Score newScore = calculateBaseScore();
        boolean hasAceInCards = cards.stream()
                .anyMatch(Card::isAce);

        if (hasAceInCards) {
            newScore = newScore.addAceScoreOnNotBust();
        }
        this.score = newScore;
    }

    private Score calculateBaseScore() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Score.zero(), Score::add);
    }

    public boolean isBlackJack() {
        return score.isBlackJackScore() && cards.size() == INIT_CARD_COUNT;
    }

    public boolean isBusted() {
        return score.isBusted();
    }

    public boolean hasScoreGreaterThan(int other) {
        return score.isGreaterThan(other);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getScore() {
        return score.toInt();
    }
}
