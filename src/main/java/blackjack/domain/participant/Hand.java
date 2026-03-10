package blackjack.domain.participant;

import blackjack.domain.GameScore;
import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int ACE_SCORE_DIFFERENCE = 10;

    private List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public GameScore calculateTotalScore() {
        GameScore score = calculateCardScore();
        int aceCount = calculateAceCount();

        while (score.isBustWithAce(aceCount)) {
            score = score.minus(ACE_SCORE_DIFFERENCE);
            aceCount--;
        }

        return score;
    }

    private GameScore calculateCardScore() {
        int sum = cards.stream()
                .mapToInt(Card::getCardValue)
                .sum();
        return new GameScore(sum);
    }

    private int calculateAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }
}
