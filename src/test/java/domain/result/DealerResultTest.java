package domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerResultTest {
    @Test
    @DisplayName("Map 의 Value 값이 음수일 때 예외처리 테스트")
    void validateCountTest() {
        Map<Result, Integer> dealerResult = new HashMap<>();
        Arrays.stream(Result.values())
            .forEach(result -> dealerResult.put(result, -1));
        assertThatThrownBy(() -> new DealerResult(dealerResult))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void equals() {
        Map<Result, Integer> testCase = new HashMap<>();
        testCase.put(Result.WIN, 1);
        testCase.put(Result.DRAW, 1);
        testCase.put(Result.LOSE, 0);

        Map<Result, Integer> copiedTestCase = new HashMap<>();
        copiedTestCase.put(Result.WIN, 1);
        copiedTestCase.put(Result.DRAW, 1);
        copiedTestCase.put(Result.LOSE, 0);

        assertThat(new DealerResult(testCase).equals(new DealerResult(copiedTestCase))).isTrue();
    }
}
