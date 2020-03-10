package util;

import domain.*;

import java.util.*;

public class ResultCalculator {

    public static Results getResults(Dealer dealer , Players players){
        List<Result> results = new ArrayList<>();

        Map<User, Boolean> summary = players.getSummary(dealer);
        Set<Map.Entry<User, Boolean>> entries = summary.entrySet();

        for (Map.Entry<User, Boolean> s : entries) {
            results.add(new Result(s.getKey(), Arrays.asList(s.getValue())));
        }
        return new Results(results);
    }
}
