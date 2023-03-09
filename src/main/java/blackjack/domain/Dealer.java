package blackjack.domain;

import static blackjack.domain.WinResult.LOSE;
import static blackjack.domain.WinResult.PUSH;
import static blackjack.domain.WinResult.WIN;

public class Dealer extends Participant {

    private static final int CAN_HIT_LIMIT = 17;

    public GameResult judgeGameResult(Players players) {
        GameResult gameResult = new GameResult();
        players.getPlayers().forEach(player -> gameResult.addResult(player, getPlayerWinResult(player)));

        return gameResult;
    }

    private WinResult getPlayerWinResult(Player player) {
        if (player.isBust()) {
            return LOSE;
        }

        if (isBust()) {
            return WIN;
        }

        return compareSum(player);
    }

    private WinResult compareSum(Player player) {
        if (player.getSum() == getSum()) {
            return PUSH;
        }

        if (player.getSum() > getSum()) {
            return WIN;
        }

        return LOSE;
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }

    @Override
    public boolean canHit() {
        return hand.getSum() < CAN_HIT_LIMIT;
    }
}
