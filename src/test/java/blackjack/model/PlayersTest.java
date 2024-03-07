package blackjack.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayersTest {
    @Test
    @DisplayName("참여자 수는 1명 이상이 아니면 예외가 발생한다")
    void validatePlayersCountTest() {
        // when & then
        assertThatThrownBy(() -> new Players(List.of(), () -> new Card(Suit.HEART, Denomination.TWO)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideDuplicatedNames")
    @DisplayName("참여자 이름은 중복되면 예외가 발생한다")
    void validateDuplicatedNamesTest(List<String> names) {
        // when & then
        assertThatThrownBy(() -> new Players(names, () -> new Card(Suit.HEART, Denomination.TWO)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<List<String>> provideDuplicatedNames() {
        return Stream.of(
                List.of("mia", "mia", "dora"),
                List.of("dora", "dora", "dora")
        );
    }
}
