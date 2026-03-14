package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Judge {
    private static final int BLACKJACK_NUMBER = 21;
    private static final int BLACKJACK_HAND_SIZE = 2;
    private final Map<Player, WinningStatus> playerResults;

    public Judge(Map<Player, WinningStatus> playerResults) {
        this.playerResults = new HashMap<>(playerResults);
    }

    public static Judge from(Dealer dealer, List<Player> players) {
        Map<Player, WinningStatus> playerResults = new HashMap<>();
        for (Player player : players) {
            playerResults.put(player, calculateWinningStatus(dealer, player));
        }
        return new Judge(playerResults);
    }

    private static WinningStatus calculateWinningStatus(Dealer dealer, Player player) {
        WinningStatus blackJackResult = resolveBlackJack(player, dealer);
        if (blackJackResult != null) {
            return blackJackResult;
        }
        WinningStatus burstResult = resolveBurst(player, dealer);
        if (burstResult != null) {
            return burstResult;
        }
        return compareResult(player, dealer);
    }

    private static boolean isBlackJack(Participant participant) {
        return participant.getHandSize() == BLACKJACK_HAND_SIZE && participant.getScore() == BLACKJACK_NUMBER;
    }

    private static WinningStatus resolveBlackJack(Player player, Dealer dealer) {
        boolean playerBlackJack = isBlackJack(player);
        boolean dealerBlackJack = isBlackJack(dealer);

        if (playerBlackJack && dealerBlackJack) {
            return WinningStatus.DRAW;
        }
        if (playerBlackJack) {
            return WinningStatus.BLACKJACK_WIN;
        }
        return null;
    }

    private static WinningStatus resolveBurst(Player player, Dealer dealer) {
        if (player.isBurst()) {
            return WinningStatus.LOSE;
        }
        if (dealer.isBurst()) {
            return WinningStatus.WIN;
        }
        return null;
    }

    private static WinningStatus compareResult(Player player, Dealer dealer) {
        if (player.getScore() > dealer.getScore()) {
            return WinningStatus.WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return WinningStatus.DRAW;
        }
        return WinningStatus.LOSE;
    }

    public int judgeDealerWinCount() {
        return (int) playerResults.values().stream()
                .filter(status -> status == WinningStatus.LOSE)
                .count();
    }

    public int judgeDealerLoseCount() {
        return (int) playerResults.values().stream()
                .filter(status -> status == WinningStatus.WIN)
                .count();
    }

    public WinningStatus getPlayerResult(Player player) {
        return playerResults.get(player);
    }

    public Map<Player, WinningStatus> getPlayerResults() {
        return new HashMap<>(playerResults);
    }
}
