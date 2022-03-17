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

    public int isWinner(Dealer dealer) {
        if (isBusted() || dealer.isWinner(this) || (!isBlackJack() && dealer.isBlackJack())) {
            return bet.getLosingMoney();
        }
        if (dealer.hasSameScore(this) && (bothBlackJack(dealer) || bothNotBlackJack(dealer))) {
            return bet.getDrawMoney();
        }
        if (cards.isBlackJack()) {
            return bet.getBlackJackPrize();
        }
        return bet.getWinningPrize();
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
