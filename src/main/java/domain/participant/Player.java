package domain.participant;

public class Player extends Participant {

    private static final int COMPARE_CRITERIA = 0;

    public Player(String name) {
        super(name);
    }

    public Result isWin(Dealer dealer) {
        if (compareTo(dealer) > COMPARE_CRITERIA) {
            return Result.WIN;
        }
        if (compareTo(dealer) < COMPARE_CRITERIA) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    @Override
    public boolean canDrawCard() {
        return cards.calculateSum() < MAX_SCORE;
    }
}
