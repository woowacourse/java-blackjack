package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Result;
import java.util.List;

public class Player extends Participant {

    private static final int ACE_ADDITIONAL_NUMBER = 10;
    private static final int BEST_SCORE = 21;

    private Player(Name name) {
        super(name);
    }

    public static Player of(String name) {
        return new Player(new Name(name));
    }

    @Override
    public boolean isReceivable() {
        return calculateBestScore() <= BEST_SCORE;
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
        if (card.isAce() && sum + ACE_ADDITIONAL_NUMBER <= BEST_SCORE) {
            sum += ACE_ADDITIONAL_NUMBER;
        }
        return sum;
    }

    public Result judgeResult(int score) {
        if (isBusted(score) && isBusted(calculateBestScore()) || calculateBestScore() == score) {
            return Result.TIE;
        }
        if (isBusted(calculateBestScore()) || (!isBusted(score) && !isCloserToBestScore(score))) {
            return Result.LOSE;
        }
        return Result.WIN;
    }

    private boolean isBusted(int score) {
        return score > BEST_SCORE;
    }

    private boolean isCloserToBestScore(int score) {
        return calculateBestScore() <= BEST_SCORE && calculateBestScore() > score;
    }
}
