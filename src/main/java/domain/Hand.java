package domain;

import static domain.GameResult.BLACKJACK_SCORE;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int ACE_SCORE_DIFFERENCE = 10;

    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int calculate() {
        int score = calculateCardScore();
        int aCount = calculateAceCount();

        while (isBustWithAce(aCount, score)) {
            score -= ACE_SCORE_DIFFERENCE;
            aCount--;
        }
        return score;
    }

    private int calculateCardScore() {
        return cards.stream()
                .mapToInt(Card::getCardValue)
                .sum();
    }

    private int calculateAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private boolean isBustWithAce(int aCount, int score) {
        return aCount > 0 && score > BLACKJACK_SCORE;
    }
}
