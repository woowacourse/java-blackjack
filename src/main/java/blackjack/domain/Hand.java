package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int ACE_DIFFERENCE = 10;
    private static final int BLACKJACK_THRESHOLD = 21;

    private final List<TrumpCard> cards;

    private Hand(List<TrumpCard> cards) {
        this.cards = List.copyOf(cards);
    }

    public static Hand init() {
        return new Hand(new ArrayList<>());
    }

    public Hand receive(TrumpCard card) {
        List<TrumpCard> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Hand(newCards);
    }

    public int countCards() {
        return cards.size();
    }

    public int calculateScore() {
        int totalScore = sumCardScores();
        return adjustForAces(totalScore);
    }

    private int sumCardScores() {
        return cards.stream()
                .mapToInt(TrumpCard::score)
                .sum();
    }

    private int adjustForAces(int totalScore) {
        int aceCount = countAces();
        while (aceCount > 0 && totalScore > BLACKJACK_THRESHOLD) {
            totalScore -= ACE_DIFFERENCE;
            aceCount--;
        }
        return totalScore;
    }

    private int countAces() {
        return (int) cards.stream()
                .filter(TrumpCard::isAce)
                .count();
    }

    public List<TrumpCard> getCards() {
        return cards;
    }
}
