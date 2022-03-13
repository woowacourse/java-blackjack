package blackJack.domain.participant;

import blackJack.domain.result.WinOrLose;

public class Player extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final String ERROR_MESSAGE_PROHIBIT_NAME = "플레이어의 이름은 '딜러'일 수 없습니다.";

    public Player(String name) {
        super(name);
        validateProhibitName(name);
    }

    private void validateProhibitName(String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PROHIBIT_NAME);
        }
    }

    @Override
    public boolean hasNextTurn() {
        return !WinOrLose.overBlackJackScore(this.getScore());
    }

    public WinOrLose getMatchResult(Dealer dealer) {
        return calculateWinOrLose(dealer.getScore());
    }

    private WinOrLose calculateWinOrLose(int dealerScore) {
        if (WinOrLose.overBlackJackScore(this.getScore())) {
            return WinOrLose.LOSE;
        }
        if (WinOrLose.overBlackJackScore(dealerScore)) {
            return WinOrLose.WIN;
        }
        return getWinOrLose(dealerScore);
    }

    private WinOrLose getWinOrLose(int dealerScore) {
        if (this.getScore() > dealerScore) {
            return WinOrLose.WIN;
        }
        if (this.getScore() == dealerScore) {
            return WinOrLose.DRAW;
        }
        return WinOrLose.LOSE;
    }
}
