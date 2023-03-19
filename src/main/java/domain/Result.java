package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

    private final Referee referee;
    private final Map<Gambler, Integer> winOrLoseResult;
    private final Map<Gambler, Integer> benefits;

    public Result(Players players, Dealer dealer, Bettings bettings) {
        referee = new Referee(dealer, bettings);
        winOrLoseResult = createResult(players, dealer);
        benefits = createBenefits(players, dealer);
    }

    private Map<Gambler, Integer> createResult(Players players, Dealer dealer) {
        Map<Gambler, Integer> result = initResults(players, dealer);
        for (Player player : players.getPlayers()) {
            referee.decideWinner(player, result);
        }

        return result;
    }

    private Map<Gambler, Integer> initResults(Players players, Dealer dealer) {
        Map<Gambler, Integer> result = new LinkedHashMap<>();
        result.put(dealer, 0);
        for (Player player : players.getPlayers()) {
            result.put(player, 0);
        }
        return result;
    }

    private Map<Gambler, Integer> createBenefits(Players players, Dealer dealer) {
        return referee.calculateBenefits(players, dealer);
    }

    public Map<Gambler, Integer> getWinOrLoseResult() {
        return winOrLoseResult;
    }

    public Map<Gambler, Integer> getBenefits() {
        return benefits;
    }
}
