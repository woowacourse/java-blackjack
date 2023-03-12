package blackjack.domain.result;

import blackjack.domain.user.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserResults {

    private static final int PLAYER_RESULT_INDEX = 0;

    private final HashMap<User, List<Result>> results;

    private UserResults(HashMap<User, List<Result>> results) {
        this.results = results;
    }

    public static UserResults of(Users users) {
        return new UserResults(calculateResults(users.getDealer(), users.getPlayers()));
    }

    public HashMap<User, List<Result>> getResults() {
        return results;
    }

    public Result getResultOf(Player player) {
        return results.get(player).get(PLAYER_RESULT_INDEX);
    }

    private static HashMap<User, List<Result>> calculateResults(Dealer dealer, Players players) {
        HashMap<User, List<Result>> userResults = initializeResults(dealer, players);
        int dealerScore = dealer.getTotalScore();
        for (Player player : players.getPlayers()) {
            Result playerResult = Result.calculateResult(player.getTotalScore(), dealerScore);
            Result dealerResult = playerResult.ofOppositeResult();
            userResults.get(player).add(playerResult);
            userResults.get(dealer).add(dealerResult);
        }
        return userResults;
    }

    private static HashMap<User, List<Result>> initializeResults(Dealer dealer, Players players) {
        HashMap<User, List<Result>> userResults = new HashMap<>();
        userResults.put(dealer, new ArrayList<>());
        for (Player player : players.getPlayers()) {
            userResults.put(player, new ArrayList<>());
        }
        return userResults;
    }
}
