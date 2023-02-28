package domain;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayersTest {

    @DisplayName("참여 인원은 1명 이상 4명 이하이다")
    @ParameterizedTest
    @MethodSource("parameterProvider")
    void playerCount1_4(List<String> names) {
        Assertions.assertThatNoException()
            .isThrownBy(() -> Players.of(names));
    }

    static Stream<List<String>> parameterProvider() {
        return Stream.of(
            List.of("a"),
            List.of("a", "kiara", "ash", "woowa")
        );
    }
}
