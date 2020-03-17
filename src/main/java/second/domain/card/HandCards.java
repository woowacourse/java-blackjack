package second.domain.card;

import second.domain.ICardDeck;
import second.domain.score.ScoreCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HandCards {
    private final List<Card> cards;
    private Score score;

    public HandCards(final List<Card> cards) {
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

    public boolean isLargerThan(Score score) {
        return this.score.isLargerThan(score);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
