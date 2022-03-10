package domain.participant;

public class Player extends Participant {

    public static final int MAX_CARD_SUM = 21;

    public Player(String name) {
        super(name);
    }

    public Result isWin(Dealer dealer) {
        if (compareTo(dealer) > 0) {
            return Result.WIN;
        }
        if (compareTo(dealer) < 0) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    @Override
    public boolean canDrawCard() {
        return cards.calculateSum() < MAX_CARD_SUM;
    }
}
