package blackjack.domain.result;

import blackjack.domain.player.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UserResultsDTO {
    private static final int EXISTS = 0;
    private static final String EMPTY_STRING = "";
    private static final String DEALER = "딜러";

    private final HashMap<User, String> results;

    private UserResultsDTO(HashMap<User, String> results) {
        this.results = results;
    }

    public static UserResultsDTO of(UserResults wrappedUserResults) {
        HashMap<User, String> newResults = new HashMap<>();
        HashMap<User, List<Result>> userResults = wrappedUserResults.getResults();
        for (User user : userResults.keySet()) {
            String result = getResult(userResults, user);
            newResults.put(user, result);
        }
        return new UserResultsDTO(newResults);
    }

    private static String getResult(HashMap<User, List<Result>> userResults, User user) {
        if (user.isDealer(user)) {
            return makeDealerResult(userResults.get(user));
        }
        return userResults.get(user).stream()
                .map(Result::getResult)
                .collect(Collectors.joining(" "));
    }

    private static String makeDealerResult(List<Result> results) {
        return Arrays.stream(Result.values())
                .map(result -> UserResultsDTO.combineResults(results, result))
                .collect(Collectors.joining(" "));
    }

    private static String combineResults(List<Result> results, Result resultType) {
        int resultCount = countResultSize(results, resultType);
        if (resultCount > EXISTS) {
            return Result.WIN.getResult();
        }
        return EMPTY_STRING;
    }

    private static int countResultSize(List<Result> results, Result resultName) {
        return (int) results.stream()
                .filter(result -> result.equals(resultName))
                .count();
    }

    public HashMap<User, String> getResults() {
        return results;
    }

    public String removeAndGetDealerResult() {
        User dealer = results.keySet().stream()
                .filter(user -> user.getName().equals(DEALER))
                .findAny()
                .get();
        return results.remove(dealer);
    }
}
