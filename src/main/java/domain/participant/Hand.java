package domain.participant;

import static domain.game.BlackJackGame.BLACKJACK_SCORE;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int ALTERNATIVE_ACE_SCORE = 1;
    private static final int ALTER_ACE_GAP = 10;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public void saveCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int sum = 0;
        for (Card card : cards) {
            sum += getScoreToAdd(card);
        }
        return sum;
    }

    private static int getScoreToAdd(final Card card) {
        if (card.isAceCard()) {
            return ALTERNATIVE_ACE_SCORE;
        }
        return card.getScore();
    }

    public int calculateResultScore() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        int aceCardCount = (int) cards.stream()
                .filter(Card::isAceCard)
                .count();
        while (aceCardCount > 0 && sum > BLACKJACK_SCORE) {
            aceCardCount--;
            sum -= ALTER_ACE_GAP;
        }
        return sum;
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
