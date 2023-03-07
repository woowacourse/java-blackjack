package domain;

import java.util.ArrayList;
import java.util.List;

public class ResultCalculator {

    private ResultCalculator() {
    }

    public static List<Result> getWinningResult(final Dealer dealer, final List<Player> players) {
        List<Result> winningResult = new ArrayList<>();
        for (int index = 1; index < players.size(); index++) {
            winningResult.add(dealer.checkWinningResult(players.get(index)));
        }
        return winningResult;
    }

}
