package blackjack;

public class Dealer {

    private final Cards cards;
    private final SoftHandCards dealerCards;

    public Dealer(Card... cards) {
        this.cards = new Cards(cards);
        this.dealerCards = new SoftHandCards(cards);
    }

    public Result judge(Cards cards) {
        Score playerScore = cards.mixHandScore();
        if (playerScore.isBust()) {
            return Result.WIN;
        }
        if (dealerScore().isBust()) {
            return Result.LOSS;
        }
        return compare(playerScore);
    }

    private Score dealerScore() {
        return cards.mixHandScore();
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
        return cards.softHandScore().lessThan(new Score(17));
    }
}
