import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ApplicationTest {
    @DisplayName("Ace 1/11 규칙 테스트")
    @ParameterizedTest(name = "[{index}] {0} -> ace={1}")
    @MethodSource("cases")
    void drawAceTest(List<Integer> cards, int expectedAce) {
        List<Integer> result = Application.drawAce(new ArrayList<>(cards));
        int actualAce = result.get(result.size() - 1);
        assertThat(actualAce).isEqualTo(expectedAce);
    }

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(List.of(2, 4, 7), 1),
                Arguments.of(List.of(3, 6), 11),
                Arguments.of(List.of(10), 11),
                Arguments.of(List.of(10,10), 1),
                Arguments.of(List.of(8,2,4), 1),
                Arguments.of(List.of(4,5,7), 1),
                Arguments.of(List.of(), 11)
        );
    }
}
