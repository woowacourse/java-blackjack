package testutil;

import domain.participant.Bet;
import domain.participant.BetMap;
import domain.participant.Player;
import domain.participant.Players;

public final class BetTestUtil {
    public static long DEFAULT_BET_AMOUNT = 2_000_000;

    public static BetMap createSinglePlayerSet(Player player) {
        BetMap betMap = new BetMap();
        betMap.addBetAmountOf(player.getName(), new Bet(DEFAULT_BET_AMOUNT));
        return betMap;
    }

    public static BetMap createMultiPlayerSet(Players players) {
        BetMap betMap = new BetMap();
        for (Player player : players) {
            betMap.addBetAmountOf(player.getName(), new Bet(DEFAULT_BET_AMOUNT));
        }
        return betMap;
    }
}
