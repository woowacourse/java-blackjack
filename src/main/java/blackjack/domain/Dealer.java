package blackjack.domain;

import static blackjack.domain.WinResult.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    private static final int CAN_HIT_LIMIT = 17;

    public GameResult judgeGameResult(Players players) {
        Map<WinResult, Integer> dealerResult = new HashMap<>();
        dealerResult.put(WIN, 0);
        dealerResult.put(PUSH, 0);
        dealerResult.put(LOSE, 0);

        List<Result> playersResult = new ArrayList<>();

        players.getPlayers().forEach(player -> addResult(dealerResult, playersResult, player));
        return new GameResult(dealerResult, playersResult);
    }

    private void addResult(Map<WinResult, Integer> dealerResult, List<Result> playersResult, Player player) {
        if (player.isBust()) {
            dealerResult.replace(WIN, dealerResult.get(WIN) + 1);
            playersResult.add(new Result(player.getName(), WinResult.LOSE));
            return;
        }
        playersResult.add(new Result(player.getName(), judge(dealerResult, player)));
    }

    private WinResult judge(Map<WinResult, Integer> dealerResult, Player player) {
        if (isBust()) {
            dealerResult.replace(LOSE, dealerResult.get(LOSE) + 1);
            return WIN;
        }

        if (player.getSum() == getSum()) {
            dealerResult.replace(PUSH, dealerResult.get(PUSH) + 1);
            return PUSH;
        }

        if (player.getSum() > getSum()) {
            dealerResult.replace(LOSE, dealerResult.get(LOSE) + 1);
            return WIN;
        }

        dealerResult.replace(WIN, dealerResult.get(WIN) + 1);
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
