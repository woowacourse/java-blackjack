package blackjack.domain.participant;

public class Player extends Participant {

    private Player(Name name) {
        super(name);
    }

    public static Player of(String name) {
        return new Player(new Name(name));
    }

    @Override
    public boolean isReceivable() {
        return !cards.isBusted();
    }

    public Result isWinner(Dealer dealer) {
        if (isBusted() || dealer.hasHigherScore(this)) {
            return Result.LOSE;
        }
        if (dealer.hasSameScore(this)) {
            return Result.DRAW;
        }
        return Result.WIN;
    }
}
