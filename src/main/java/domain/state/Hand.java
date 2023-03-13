package domain.state;

import domain.card.Card;
import java.util.List;

public class Hand {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int ACE_VALUE_DIFFERENCE = 10;
    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(final Card card) {
        this.cards.add(card);
    }

    public Score calculateScore() {
        int totalValue = this.cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        Score score = new Score(totalValue);
        return calculateAce(score.getValue());
    }

    private Score calculateAce(int score) {
        int aceCount = (int) this.cards.stream()
                .filter(Card::isAce)
                .count();
        for (int i = 0; i < aceCount; i++) {
            score = convertAceValue(score);
        }
        return new Score(score);
    }

    private int convertAceValue(int score) {
        if (score > BLACKJACK_NUMBER) {
            score -= ACE_VALUE_DIFFERENCE;
        }
        return score;
    }

    public List<Card> getCards() {
        return cards;
    }
}
