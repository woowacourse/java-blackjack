package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import java.util.List;

public class Player extends Participant {

    private Player(Name name) {
        super(name);
    }

    public static Player of(String name) {
        return new Player(new Name(name));
    }

    @Override
    public boolean isReceivable() {
        return calculateBestScore() <= BUST_THRESHOLD;
    }

    @Override
    public int calculateBestScore() {
        List<Card> cards = this.cards.getCardHand();

        int sum = cards.stream()
                .map(Card::getNumber)
                .map(Number::getScore)
                .reduce(0, Integer::sum);

        for (Card card : cards) {
            sum = getBest(sum, card);
        }

        return sum;
    }

    private int getBest(int sum, Card card) {
        if (card.isAce() && sum + ACE_ADDITIONAL_NUMBER <= BUST_THRESHOLD) {
            sum += ACE_ADDITIONAL_NUMBER;
        }
        return sum;
    }

    public boolean isWinner(int score) {
        return !isBusted(calculateBestScore()) && (isBusted(score) || isCloserToBestScore(score));
    }

    private boolean isBusted(int score) {
        return score > BUST_THRESHOLD;
    }

    private boolean isCloserToBestScore(int score) {
        return calculateBestScore() <= BUST_THRESHOLD && calculateBestScore() > score;
    }
}
