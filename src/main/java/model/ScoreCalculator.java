package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScoreCalculator {

    private Set<Integer> candidates;

    public List<Integer> calculate(Cards cards) {
        candidates = new HashSet<>();
        dfs(cards.totalWithoutAce(), 0, cards.aceCount());

        return List.copyOf(candidates);
    }

    private void dfs(int sum, int ace, int totalAce) {
        if (ace == totalAce) {
            candidates.add(sum);
            return;
        }

        dfs(sum + 1, ace + 1, totalAce);
        dfs(sum + 11, ace + 1, totalAce);
    }
}
