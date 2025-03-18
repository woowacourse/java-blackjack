package blackjack.model.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import blackjack.model.participants.ParticipantType;

public class Hand {
    private static final int BASE_VALUE = 0;
    private static final int BUST_THRESHOLD = 21;

    private final List<Card> cards;
    private int score;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void calculateScore(ParticipantType type) {
        if (ParticipantType.isDealer(type)) {
            calculateDealerScore();
            return;
        }
        calculatePlayerScore();
    }

    private void calculateDealerScore() {
        int score = BASE_VALUE;
        for (Card card : cards) {
            score += card.getRankValue().getFirst();
        }
        this.score = score;
    }

    private void calculatePlayerScore() {
        Set<Integer> possibleScores = calculatePossibleScores();
        this.score = possibleScores.stream()
                .filter(value -> value <= BUST_THRESHOLD)
                .max(Integer::compareTo)
                .orElse(Collections.min(possibleScores));
    }

    private Set<Integer> calculatePossibleScores() {
        List<Integer> scores = new ArrayList<>(List.of(BASE_VALUE));
        for (Card card : cards) {
            scores = scores.stream()
                    .flatMap(score -> card.getRankValue().stream().map(value -> score + value))
                    .toList();
        }
        return new HashSet<>(scores);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

    public int getHandSize() {
        return cards.size();
    }
}
