package blackjack.domain.participant;

public class Player extends Participant {

    private final Bet bet;

    private Player(Name name, Bet bet) {
        super(name);
        this.bet = bet;
    }

    public static Player of(String name, int bet) {
        return new Player(new Name(name), new Bet(bet));
    }

    @Override
    public boolean isReceivable() {
        return !cards.isBusted();
    }

    public int calculatePrize(Dealer dealer) {
        if (isBusted() || dealer.isWinner(this) || (!isBlackJack() && dealer.isBlackJack())) {
            return bet.calculateLosingPrize();
        }
        if (dealer.hasSameScore(this) && (bothBlackJack(dealer) || bothNotBlackJack(dealer))) {
            return bet.calculateDrawPrize();
        }
        if (cards.isBlackJack()) {
            return bet.calculateBlackJackPrize();
        }
        return bet.calculateWinningPrize();
    }

    private boolean bothBlackJack(Dealer dealer) {
        return isBlackJack() && dealer.isBlackJack();
    }

    private boolean bothNotBlackJack(Dealer dealer) {
        return !isBlackJack() && !dealer.isBlackJack();
    }

    public Bet getBet() {
        return bet;
    }
}
