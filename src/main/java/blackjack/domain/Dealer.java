package blackjack.domain;

import static blackjack.domain.WinResult.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    private static final int CARD_TAKE_LIMIT = 17;
    private final Map<WinResult, Integer> result;

    public Dealer() {
        result = new HashMap<>();
        result.put(WIN, 0);
        result.put(PUSH, 0);
        result.put(LOSE, 0);
    }

    public GameResult judgeGameResult(Players players) {
        List<Result> playersResult = new ArrayList<>();
        players.getPlayers().forEach(player -> addResult(playersResult, player));
        return new GameResult(result, playersResult);
    }

    private void addResult(List<Result> playersResult, Player player) {
        if (player.isBust()) {
            playersResult.add(new Result(player.getName(), WinResult.LOSE));
            return;
        }
        playersResult.add(new Result(player.getName(), judge(player)));
    }

    private WinResult judge(Player player) {
        if (isBust()) {
            result.replace(LOSE, result.get(LOSE) + 1);
            return WIN;
        }

        if (player.getSum() == getSum()) {
            result.replace(PUSH, result.get(PUSH) + 1);
            return PUSH;
        }

        if (player.getSum() > getSum()) {
            result.replace(LOSE, result.get(LOSE) + 1);
            return WIN;
        }

        result.replace(WIN, result.get(WIN) + 1);
        return LOSE;
    }

    @Override
    public boolean canHit() {
        return hand.getSum() < CARD_TAKE_LIMIT;
    }
}
