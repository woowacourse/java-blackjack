import java.util.Comparator;
import java.util.List;

public class ScoreJudgement {

    public int determineNearest(List<Integer> candidates) {
        List<Integer> sortedCandidates = candidates.stream()
                .sorted(Comparator.comparing(Integer::intValue))
                .toList()
                .reversed();

        return sortedCandidates.stream()
                .filter(candidate -> !isOverCriterion(candidate))
                .findFirst()
                .orElse(0);
    }

    private boolean isOverCriterion(int total) {
        return total > 21;
    }

    // 이긴 쪽 왼쪽 -1 오른쪽 1
    public int judgeWinner(int total1, int total2) {
        if (21 - total1 < 21 - total2) {
            return -1;
        }

        if (21 - total1 > 21 - total2) {
            return 1;
        }

        return 0;
    }
}
