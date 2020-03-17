package domain.card.cardDrawable;

import domain.card.Card;
import domain.card.providable.CardProvidable;
import domain.result.Score;
import domain.result.ScoreCalculable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand implements CardDrawable, ScoreCalculable {
    private static final String DELIMITER = ", ";
    private static final int FIRST_INDEX = 0;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public void drawCard(CardProvidable cardProvidable) {
        cards.add(cardProvidable.giveCard());
    }

    @Override
    public Score calculateScore() {
        int defaultSum = calculateDefaultSum();

        if (this.hasAce()) {
            return new Score(updateAceScore(defaultSum));
        }

        return new Score(defaultSum);
    }

    private int calculateDefaultSum() {
        return cards.stream()
                .map(Card::extractScore)
                .mapToInt(Score::getValue)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private int updateAceScore(int score) {
        if (score + ACE_ADDITIONAL_SCORE <= BLACK_JACK_SCORE) {
            return score + ACE_ADDITIONAL_SCORE;
        }

        return score;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card getOneCard() {
        return cards.get(FIRST_INDEX);
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(DELIMITER));
    }
}
