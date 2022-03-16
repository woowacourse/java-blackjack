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
        if (isBusted() || dealer.hasHigherScore(this) || (!isBlackJack() && dealer.isBlackJack())) {
            return Result.LOSE;
        }
        if (dealer.hasSameScore(this) && (bothBlackJack(dealer) || bothNotBlackJack(dealer))) {
            return Result.DRAW;
        }
        return Result.WIN;
    }

    private boolean bothBlackJack(Dealer dealer) {
        return isBlackJack() && dealer.isBlackJack();
    }

    private boolean bothNotBlackJack(Dealer dealer) {
        return !isBlackJack() && !dealer.isBlackJack();
    }
}
