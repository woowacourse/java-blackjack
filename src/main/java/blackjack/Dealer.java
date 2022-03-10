package blackjack;

public class Dealer {

    private final MixHandCards mixHandCards;
    private final SoftHandCards softHandCards;

    public Dealer(Card... cards) {
        this.mixHandCards = new MixHandCards(cards);
        this.softHandCards = new SoftHandCards(cards);
    }

    public Result judge(MixHandCards cards) {
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
        return mixHandCards.score();
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
        return softHandCards.score().lessThan(new Score(17));
    }
}
