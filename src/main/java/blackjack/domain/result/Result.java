package blackjack.domain.result;

import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {

    private final List<Player> winners = new ArrayList<>();
    private final List<Player> losers = new ArrayList<>();

    private final Map<Player, Grade> result = new HashMap<>();

    public Result(final Players players) {
        for (Player player : players.getPlayers()) {
            result.put(player, Grade.PROCEED);
        }
    }

    public boolean isKeepPlaying(final Dealer dealer) {
        grade(dealer);
        return result.values().stream()
                .anyMatch(grade -> grade.equals(Grade.PROCEED));
    }

    public void compete(final Dealer dealer) {
        final List<Player> proceedPlayers = getProceedPlayers();
        for (Player player : proceedPlayers) {
            result.put(player, Grade.grade(dealer, player));
        }
    }

    public int numberOfWinners() {
        return winners.size();
    }

    public int numberOfLosers() {
        return losers.size();
    }

    public boolean contains(final Player player) {
        return winners.contains(player);
    }

    private void grade(final Dealer dealer) {
        result.replaceAll((player, grade) -> Grade.gradeToInitCards(dealer, player));
    }

    private List<Player> getProceedPlayers() {
        return result.keySet().stream()
                .filter(player -> result.get(player).equals(Grade.PROCEED))
                .collect(Collectors.toList());
    }

    public Grade getGrade(final Player player) {
        return result.get(player);
    }
}
