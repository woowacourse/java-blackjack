package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;


public class StreamTest {
    private static final int DATA_SIZE = 200_000;
    private static final int MATCH_START = 100_000;
    private static final int WARM_UP_COUNT = 5;
    private static final int MEASURE_COUNT = 10;
    private static final int CPU_WORK = 200;

    private static final int LOG_DATA_SIZE = 20;
    private static final int LOG_MATCH_START = 10;

    @Test
    void 순차_스트림_findFirst_findAny_수행시간_비교() {
        BenchmarkResult findFirstResult = benchmark(this::sequentialFindFirst);
        BenchmarkResult findAnyResult = benchmark(this::sequentialFindAny);

        assertThat(findFirstResult.value()).isEqualTo(MATCH_START);
        assertThat(findAnyResult.value()).isGreaterThanOrEqualTo(MATCH_START);

        printBenchmark("순차 스트림", findFirstResult, findAnyResult);
    }

    @Test
    void 병렬_스트림_findFirst_findAny_수행시간_비교() {
        BenchmarkResult findFirstResult = benchmark(this::parallelFindFirst);
        BenchmarkResult findAnyResult = benchmark(this::parallelFindAny);

        assertThat(findFirstResult.value()).isEqualTo(MATCH_START);
        assertThat(findAnyResult.value()).isGreaterThanOrEqualTo(MATCH_START);

        printBenchmark("병렬 스트림", findFirstResult, findAnyResult);
    }

    @Test
    void 순차_스트림_findFirst_findAny_접근요소순서_출력() {
        EvaluationResult findFirstResult = sequentialFindFirstWithLog();
        EvaluationResult findAnyResult = sequentialFindAnyWithLog();

        assertThat(findFirstResult.value()).isEqualTo(LOG_MATCH_START);
        assertThat(findAnyResult.value()).isGreaterThanOrEqualTo(LOG_MATCH_START);

        printEvaluationOrder("순차 스트림", findFirstResult, findAnyResult);
    }

    @Test
    void 병렬_스트림_findFirst_findAny_접근요소순서_출력() {
        EvaluationResult findFirstResult = parallelFindFirstWithLog();
        EvaluationResult findAnyResult = parallelFindAnyWithLog();

        assertThat(findFirstResult.value()).isEqualTo(LOG_MATCH_START);
        assertThat(findAnyResult.value()).isGreaterThanOrEqualTo(LOG_MATCH_START);

        printEvaluationOrder("병렬 스트림", findFirstResult, findAnyResult);
    }

    private int sequentialFindFirst() {
        return IntStream.rangeClosed(1, DATA_SIZE)
                .filter(this::isMatch)
                .findFirst()
                .orElseThrow();
    }

    private int sequentialFindAny() {
        return IntStream.rangeClosed(1, DATA_SIZE)
                .filter(this::isMatch)
                .findAny()
                .orElseThrow();
    }

    private int parallelFindFirst() {
        return IntStream.rangeClosed(1, DATA_SIZE)
                .parallel()
                .filter(this::isMatch)
                .findFirst()
                .orElseThrow();
    }

    private int parallelFindAny() {
        return IntStream.rangeClosed(1, DATA_SIZE)
                .parallel()
                .filter(this::isMatch)
                .findAny()
                .orElseThrow();
    }

    private EvaluationResult sequentialFindFirstWithLog() {
        List<Integer> log = new ArrayList<>();
        int value = IntStream.rangeClosed(1, LOG_DATA_SIZE)
                .filter(number -> logAndMatch(number, log))
                .findFirst()
                .orElseThrow();

        return new EvaluationResult(value, log);
    }

    private EvaluationResult sequentialFindAnyWithLog() {
        List<Integer> log = new ArrayList<>();
        int value = IntStream.rangeClosed(1, LOG_DATA_SIZE)
                .filter(number -> logAndMatch(number, log))
                .findAny()
                .orElseThrow();

        return new EvaluationResult(value, log);
    }

    private EvaluationResult parallelFindFirstWithLog() {
        List<Integer> log = synchronizedLog();
        int value = IntStream.rangeClosed(1, LOG_DATA_SIZE)
                .parallel()
                .filter(number -> logAndMatch(number, log))
                .findFirst()
                .orElseThrow();

        return new EvaluationResult(value, log);
    }

    private EvaluationResult parallelFindAnyWithLog() {
        List<Integer> log = synchronizedLog();
        int value = IntStream.rangeClosed(1, LOG_DATA_SIZE)
                .parallel()
                .filter(number -> logAndMatch(number, log))
                .findAny()
                .orElseThrow();

        return new EvaluationResult(value, log);
    }

    private boolean isMatch(int value) {
        int checksum = consumeCpu(value);

        if (checksum == Integer.MIN_VALUE) {
            throw new IllegalStateException("도달할 수 없는 분기");
        }
        return value >= MATCH_START;
    }

    private int consumeCpu(int value) {
        int result = value;

        for (int index = 0; index < CPU_WORK; index++) {
            result = result * 31 + index;
            result = result ^ (result >>> 7);
        }
        return result;
    }

    private BenchmarkResult benchmark(IntSupplier experiment) {
        warmUp(experiment);

        long totalNanos = 0L;
        int lastValue = 0;

        for (int count = 0; count < MEASURE_COUNT; count++) {
            long start = System.nanoTime();
            lastValue = experiment.getAsInt();
            totalNanos += System.nanoTime() - start;
        }

        long averageNanos = totalNanos / MEASURE_COUNT;
        return new BenchmarkResult(averageNanos, lastValue);
    }

    private void warmUp(IntSupplier experiment) {
        for (int count = 0; count < WARM_UP_COUNT; count++) {
            experiment.getAsInt();
        }
    }

    private boolean logAndMatch(int value, List<Integer> log) {
        log.add(value);
        delayForObservation(value);
        return value >= LOG_MATCH_START;
    }

    private void delayForObservation(int value) {
        long delayMillis = 1L;

        if (value <= 10) {
            delayMillis = 5L;
        }
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(delayMillis));
    }

    private List<Integer> synchronizedLog() {
        return Collections.synchronizedList(new ArrayList<>());
    }

    private void printBenchmark(
            String label,
            BenchmarkResult findFirstResult,
            BenchmarkResult findAnyResult
    ) {
        System.out.println();
        System.out.println("[" + label + "]");
        System.out.printf("findFirst 반환값: %d%n", findFirstResult.value());
        System.out.printf("findAny 반환값: %d%n", findAnyResult.value());
        System.out.printf(
                "findFirst 평균 실행시간: %,d ns (%.3f ms)%n",
                findFirstResult.averageNanos(),
                nanosToMillis(findFirstResult.averageNanos())
        );
        System.out.printf(
                "findAny 평균 실행시간: %,d ns (%.3f ms)%n",
                findAnyResult.averageNanos(),
                nanosToMillis(findAnyResult.averageNanos())
        );
        System.out.printf(
                "findAny 개선율(findFirst 대비): %.2f%%%n",
                improvementRate(findFirstResult.averageNanos(), findAnyResult.averageNanos())
        );
    }

    private void printEvaluationOrder(
            String label,
            EvaluationResult findFirstResult,
            EvaluationResult findAnyResult
    ) {
        System.out.println();
        System.out.println("[" + label + "]");
        System.out.printf("findFirst 반환값: %d%n", findFirstResult.value());
        System.out.printf("findFirst 접근 요소 순서: %s%n", findFirstResult.accessedValues());
        System.out.printf("findAny 반환값: %d%n", findAnyResult.value());
        System.out.printf("findAny 접근 요소 순서: %s%n", findAnyResult.accessedValues());
    }

    private double nanosToMillis(long nanos) {
        return nanos / 1_000_000.0;
    }

    private double improvementRate(long baselineNanos, long targetNanos) {
        if (baselineNanos == 0L) {
            return 0.0;
        }
        return ((double) (baselineNanos - targetNanos) / baselineNanos) * 100.0;
    }

    private record BenchmarkResult(long averageNanos, int value) {
    }

    private record EvaluationResult(int value, List<Integer> accessedValues) {
    }
}
