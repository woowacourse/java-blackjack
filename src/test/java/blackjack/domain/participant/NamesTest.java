package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class NamesTest {

    private static Stream<Arguments> provideNames() {
        return Stream.of(
                Arguments.of(List.of()),
                Arguments.of(List.of("pobi", "crong", "honux", "wannte", "디디", "누누"))
        );
    }

    @Test
    void 이름이_목록이_null_이면_예외() {
        assertThatThrownBy(() -> new Names(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("사용자 이름이 입력되지 않았습니다");
    }

    @ParameterizedTest
    @MethodSource("provideNames")
    void 이름의_수가_0부터_5까지만_가능하다(final List<String> playerNames) {
        assertThatThrownBy(() -> new Names(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("사용자 수는 1 이상 5 이하여야 합니다. 현재 : " + playerNames.size() + " 명입니다");
    }

    @Test
    void 이름이_이름이_중복되면_예외() {
        final List<String> playerNames = List.of("pobi", "pobi", "honux", "wannte", "디디");

        assertThatThrownBy(() -> new Names(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("사용자의 이름이 중복됩니다.");
    }
}
