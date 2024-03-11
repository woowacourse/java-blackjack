package domain.score;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.score.Status.values;

public class Score {

    private final Map<Status, Integer> score;

    public Score() {
        score = Arrays.stream(values())
                .collect(Collectors.toMap(
                        status -> status,
                        integer -> 0
                ));
    }

    public void increaseScore(Status status) {
        Integer presentScore = score.get(status);
        score.put(status, presentScore + 1);
    }

    public int getScore(Status status) {
        return score.get(status);
    }
}
