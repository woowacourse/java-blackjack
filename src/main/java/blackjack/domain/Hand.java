package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int ACE_DIFFERENCE = 10;
    private static final int BLACKJACK_THRESHOLD = 21;

    private final List<TrumpCard> cards;

    private Hand() {
        this.cards = new ArrayList<>();
    }

    public static Hand init() {
        return new Hand();
    }

    public void add(TrumpCard card) {
        cards.add(card);
    }

    public boolean isBlackjack(){
        return calculateScore() == BLACKJACK_THRESHOLD;
    }
    public boolean isBust() {
        return calculateScore() > BLACKJACK_THRESHOLD;
    }

    public int calculateScore() {
        int totalScore = sumCardScores();
        return adjustForAces(totalScore);
    }

    private int sumCardScores() {
        return cards.stream()
                .mapToInt(TrumpCard::getScore)
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

    public List<String> cardNames() {
        return cards.stream()
                .map(TrumpCard::name)
                .toList();
    }

    public int countCards() {
        return cards.size();
    }

    public TrumpCard getFirstCard(){
        return cards.getFirst();
    }

    public List<TrumpCard> getCards() {
        return List.copyOf(cards);
    }
}
