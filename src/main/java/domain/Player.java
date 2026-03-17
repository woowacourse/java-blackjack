package domain;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        return !state.isFinished();
    }

    public WinningStatus calculateResult(Score dealerScore) {
        if (isBlackJack() && !dealerScore.isBlackJack()) {
            return WinningStatus.BLACKJACK_WIN;
        }
        if (dealerScore.isBlackJack() && !isBlackJack()) {
            return WinningStatus.LOSE;
        }
        if (isBurst()) {
            return WinningStatus.LOSE;
        }
        if (dealerScore.isBurst()) {
            return WinningStatus.WIN;
        }
        return new Score(state.getScore(), isBlackJack()).compareTo(dealerScore);
    }
}
