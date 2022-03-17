package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StatusTest {

    @ParameterizedTest(name = "count = {0}, score = {1}")
    @MethodSource("provideParameters")
    @DisplayName("카드 상태")
    void findStatus(int count, int sum, Status expect) {
        // then
        assertThat(Status.findStatus(count, sum)).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments(2, 21, Status.BLACKJACK),
                Arguments.arguments(3, 21, Status.NONE),
                Arguments.arguments(2, 20, Status.NONE),
                Arguments.arguments(3, 23, Status.BUST)
        );
    }
}