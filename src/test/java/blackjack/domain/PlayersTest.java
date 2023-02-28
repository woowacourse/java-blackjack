package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PlayersTest {

    @Test
    void 입력받은_사용자의_이름이_중복되는_경우_예외를_던진다() {
        List<String> names = List.of("name", "name");

        assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Players.DUPLICATE_NAMES_MESSAGE + names);
    }

    @ParameterizedTest(name = "입력받은 사용자가 {0}명인 경우 예외를 던진다.")
    @ValueSource(ints = {0, 7})
    void 입력받은_사용자가_1명_미만_6명_초과인_경우_예외를_던진다(final int count) {
        final List<String> names = IntStream.range(0, count)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Players.INVALID_NAME_COUNT + count);
    }

}
