package domain;

import java.util.ArrayList;
import java.util.List;

public class ResultCalculator {

    private ResultCalculator() {
    }

    public static List<Result> getWinningResult(final Dealer dealer, final List<Player> players) {
        List<Result> winningResult = new ArrayList<>();
        for (int index = 0; index < players.size(); index++) {
            Player player = players.get(index);
            Result result = Participant.checkWinningResult(dealer,player);
            winningResult.add(result);
        }
        return winningResult;
    }

}
