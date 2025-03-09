package blackjack.model.game;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ResultStatistic {

    private final Map<Result, ResultCount> statistic;

    public ResultStatistic() {
        this.statistic = Result.getSortedResult().stream()
                .collect(
                        Collectors.toMap(
                                result -> result,
                                result -> new ResultCount(),
                                (oldResult, newResult) -> oldResult,
                                LinkedHashMap::new
                        )
                );
    }

    public void add(final Result result) {
        statistic.get(result).increase();
    }

    public boolean hasMultipleResult() {
        return statistic.values().stream()
                .filter(ResultCount::hasMeaningfulValue)
                .count() > 1;
    }

    public Map<Result, ResultCount> getStatistic() {
        return statistic;
    }

    public static class ResultCount {

        private int value;

        ResultCount() {
            this.value = 0;
        }

        protected void increase() {
            value++;
        }

        public boolean hasMeaningfulValue() {
            return value > 0;
        }

        public int getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ResultCount that = (ResultCount) o;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }
    }
}
