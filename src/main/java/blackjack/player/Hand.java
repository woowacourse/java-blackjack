package blackjack.player;

import blackjack.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int INIT_CARD_COUNT = 2;

    private final List<Card> cards;
    private final Score score;

    public Hand(List<Card> cards) {
        this.cards = cards;
        this.score = calculateScore();
    }

    public Hand() {
        this(new ArrayList<>());
    }

    public Hand addCard(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Hand(newCards);
    }

    private Score calculateScore() {
        Score calculatedScore = calculateBaseScore();
        boolean hasAceInCards = cards.stream()
                .anyMatch(Card::isAce);

        if (hasAceInCards) {
            calculatedScore = calculatedScore.addAceScoreOnNotBust();
        }
        return calculatedScore;
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

    public Score getScore() {
        return score;
    }
}
