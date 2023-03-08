package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UserResult {

    private static final int EXISTS = 0;
    private final HashMap<User, String> results;

    public UserResult(HashMap<User, List<Result>> userResults) {
        results = makeResults(userResults);
    }

    private HashMap<User, String> makeResults(HashMap<User, List<Result>> userResults) {
        HashMap<User, String> newResults = new HashMap<>();
        for (User user : userResults.keySet()) {
            String result = getResult(userResults, user);
            newResults.put(user, result);
        }
        return newResults;
    }

    private static String getResult(HashMap<User, List<Result>> userResults, User user) {
        if (user instanceof Dealer) {
            return makeDealerResult(userResults.get(user));
        }
        return userResults.get(user).stream()
                .map(Result::getResult).collect(Collectors.joining(" "));
    }

    private static String makeDealerResult(List<Result> results) {
        StringBuilder stringBuilder = new StringBuilder();
        int winCount = (int) results.stream().filter(result -> result.equals(Result.WIN)).count();
        int loseCount = (int) results.stream().filter(result -> result.equals(Result.LOSE)).count();
        int tieCount = (int) results.stream().filter(result -> result.equals(Result.TIE)).count();
        if (winCount > EXISTS) {
            stringBuilder.append(winCount).append(Result.WIN.getResult()).append(" ");
        }
        if (loseCount > EXISTS) {
            stringBuilder.append(loseCount).append(Result.LOSE.getResult()).append(" ");
        }
        if (tieCount > EXISTS) {
            stringBuilder.append(tieCount).append(Result.TIE.getResult());
        }
        return stringBuilder.toString();
    }

    public HashMap<User, String> getResults() {
        return results;
    }
}
