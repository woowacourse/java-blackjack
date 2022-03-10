package blackjack;

public class Dealer {

    private final MixHandCards cards;
    private final SoftHandCards dealerCards;

    public Dealer(Card... cards) {
        this.cards = new MixHandCards(cards);
        this.dealerCards = new SoftHandCards(cards);
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
        return dealerCards.score().lessThan(new Score(17));
    }
}
