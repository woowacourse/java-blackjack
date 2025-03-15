package domain.card;

import domain.game.ScoreInfo;

import java.util.ArrayList;
import java.util.List;

public class HandCards {

    private final List<Card> deck = new ArrayList<>();

    public void setUpCards(Card card1, Card card2) {
        deck.add(card1);
        deck.add(card2);
    }

    public void takeMore(Card card) {
        deck.add(card);
    }

    public int calculateScore() {
        int sum = deck.stream()
            .mapToInt(Card::getNumber)
            .sum();

        if (hasAce()) {
            return calculateOptimalScore(sum);
        }
        return sum;
    }

    private boolean hasAce() {
        return deck.stream()
            .anyMatch(card -> card.rank() == Rank.ACE);
    }

    private int calculateOptimalScore(int sum){
        if (sum + Rank.ACE_LOW_HIGH_GAP <= ScoreInfo.BLACKJACK_SCORE) {
            return sum + Rank.ACE_LOW_HIGH_GAP;
        }
        return sum;
    }

    public List<Card> getCards() {
        return List.copyOf(deck);
    }
}
