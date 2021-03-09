package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class GameResult {

    private final Map<Player, WinOrLose> results;

    public GameResult() {
        results = new HashMap<>();
    }

    public void writeResult(Player dealer, Gamers gamers) {
        gamers.getGamers()
            .forEach(gamer -> results.put(gamer, WinOrLose.calculate(dealer, gamer)));
    }

    public Map<WinOrLose, Long> dealerResult() {
        return results.values()
            .stream()
            .map(WinOrLose::reverse)
            .collect(Collectors.groupingBy(
                winOrLose -> winOrLose, Collectors.counting())
            );
    }

    public Map<String, WinOrLose> gamerResult() {
        return results.entrySet()
            .stream()
            .collect(toMap(entry -> entry.getKey().getName(), Entry::getValue));
    }

    public WinOrLose result(Player player) {
        return results.get(player);
    }

    public enum WinOrLose {
        WIN("승"),
        LOSE("패");

        private final String message;

        WinOrLose(String message) {
            this.message = message;
        }

        public static WinOrLose calculate(Player dealer, Player gamer) {
            if (isOnlyDealerBursted(dealer, gamer) || hasPlayerHigherScoreThanDealer(dealer, gamer)) {
                return WinOrLose.WIN;
            }

            return WinOrLose.LOSE;
        }

        private static boolean isOnlyDealerBursted(Player dealer, Player gamer) {
            return dealer.getStatus() == Status.BURST &&
                gamer.getStatus() != Status.BURST;
        }

        private static boolean hasPlayerHigherScoreThanDealer(Player dealer, Player gamer) {
            return dealer.getStatus() != Status.BURST &&
                gamer.getStatus() != Status.BURST &&
                dealer.getScore() < gamer.getScore();
        }

        public WinOrLose reverse() {
            if (this == WIN) {
                return LOSE;
            }
            return WIN;
        }

        public String getMessage() {
            return message;
        }
    }
}
