package second.domain.card;

import second.domain.ICardDeck;
import second.domain.score.ScoreCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandCards {
    private final List<Card> cards;
    private Score score;

    public HandCards(List<Card> cards) {
        this.cards = cards;
        this.score = ScoreCalculator.calculate(this);
    }

    public HandCards() {
        this(new ArrayList<>());
    }

    public void drawCard(final ICardDeck cardDeck) {
        final Card drawCard = cardDeck.pickCard();

        addCard(drawCard);

        plusScore(drawCard);
    }

    private void plusScore(Card drawCard) {
        score = score.plus(drawCard.extractScore());
    }

    private void addCard(Card drawCard) {
        cards.add(drawCard);
    }

    public boolean isBust() {
        return score.isBust();
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int calculateDefaultSum() {
        return cards.stream()
                .mapToInt(Card::extractScore)
                .sum();
    }

    public boolean isLargerScoreThan(Score score) {
        return this.score.isLargerThan(score);
    }

    public boolean isLargerScoreThan(HandCards handCards) {
        return this.score.isLargerThan(handCards.score);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
