package blackjack;

public class Dealer {
    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    Result judge(Cards cards) {
        return judge(cards.score());
    }

    public Result judge(Score playerScore) {
        if (playerScore.isBust()) {
            return Result.WIN;
        }
        if (dealerScore().isBust()) {
            return Result.LOSS;
        }
        return compare(playerScore);
    }

    private Result compare(Score playerScore) {
        if (dealerScore().compareTo(playerScore) == -1) {
            return Result.LOSS;
        } else if(dealerScore().compareTo(playerScore) == 0) {
            return Result.DRAW;
        }
        return Result.WIN;
    }

    private Score dealerScore() {
        return cards.score();
    }
}
