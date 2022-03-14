package blackjack.domain;

public class Guest extends AbstractPlayer implements Player {

    public static final int MAX_POINT = 21;

    public Guest(String name) {
        this.cards = new Deck();
        this.name = name;
    }

    @Override
    public boolean isOverMoreCardLimit() {
        return cards.sumPoints() > MAX_POINT;
    }

    @Override
    public boolean isWin(Player guest, Player dealer) {
        int points = cards.sumPoints();
        if (dealer.isLose(points) && points <= Match.MAX_WINNER_POINT) {
            return true;
        }
        if (dealer.isOverPointLimit() && points <= Match.MAX_WINNER_POINT) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isDraw(Player dealer) {
        return cards.sumPoints() == dealer.getDeck().sumPoints();
    }
}
