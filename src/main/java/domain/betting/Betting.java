package domain.betting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Betting {
    private final Map<String, BettingAmount> values = new HashMap<>();

    public Betting(List<String> names) {
        validateDuplicateNames(names);
        for(String name : names) {
            values.put(name, null);
        }
    }

    public void betBettingAmount(String name, BettingAmount bettingAmount) {
        values.put(name, bettingAmount);
    }

    public BettingAmount getBettingAmountByName(String name) {
        return values.get(name);
    }

    public void validateDuplicateNames(List<String> names) {
        List<String> distinctNamesCount = names.stream().
                distinct().collect(Collectors.toList());
        if(distinctNamesCount.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름이 입력됩니다.");
        }
    }
}
