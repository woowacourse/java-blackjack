package domain.participant;

import static domain.GameResult.BLACKJACK_SCORE;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int ACE_SCORE_DIFFERENCE = 10;

    private final List<Card> cards;

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
        int cardScore = calculateCardScore();
        int aceCount = calculateAceCount();

        while (isBustWithAce(aceCount, cardScore)) {
            cardScore -= ACE_SCORE_DIFFERENCE;
            aceCount--;
        }
        return cardScore;
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
