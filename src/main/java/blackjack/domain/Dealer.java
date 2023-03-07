package blackjack.domain;

public class Dealer extends Participant {

    private static final int CARD_TAKE_LIMIT = 17;

    public boolean isUnderTakeLimit() {
        return computeSumOfCards() < CARD_TAKE_LIMIT;
    }

    public WinResult judge(Player player) {
        if (isBust()) {
            return WinResult.WIN;
        }

        if (player.getSum() == getSum()) {
            return WinResult.PUSH;
        }

        if (player.getSum() > getSum()) {
            return WinResult.WIN;
        }

        return WinResult.LOSE;
    }

    @Override
    public boolean canDraw() {
        int sum = computeSumOfCards();
        return sum < CARD_TAKE_LIMIT;
    }
}
