package blackjack;

public class Dealer {

    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    Result judge(Cards cards) {
        Score playerScore = cards.score();
        if (playerScore.isBust()) {
            return Result.WIN;
        }
        if (dealerScore().isBust()) {
            return Result.LOSS;
        }
        return compare(playerScore);
    }

    private Score dealerScore() {
        return cards.score();
    }

    private Result compare(Score playerScore) {
        if (dealerScore().lessThan(playerScore)) {
            return Result.LOSS;
        }
        if (dealerScore().moreThan(playerScore)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public boolean isPossibleTakeCard() {
        return cards.score().getValue() < 17;
    }
}
