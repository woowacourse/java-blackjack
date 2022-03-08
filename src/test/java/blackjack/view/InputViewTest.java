package blackjack.view;

import blackjack.view.reader.CustomReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class InputViewTest {

    private final CustomReader customReader = new CustomReader();
    private final InputView inputView = new InputView(customReader);

    @ParameterizedTest
    @MethodSource("provideForParsePlayerNamesTest")
    @DisplayName("플레이어 이름은 쉼표로 구분되어야 한다.")
    void parsePlayerNamesTest(final String inputLine, final List<String> expectedPlayerNames) {
        customReader.initTest(inputLine);

        List<String> actualPlayerNames = inputView.requestPlayerNames();

        assertThat(actualPlayerNames).isEqualTo(expectedPlayerNames);
    }

    public static Stream<Arguments> provideForParsePlayerNamesTest() {
        return Stream.of(
                Arguments.of("pobi,if,sun", List.of("pobi", "if", "sun")),
                Arguments.of("pobi, if, sun", List.of("pobi", "if", "sun")),
                Arguments.of("", List.of("")),
                Arguments.of("    ", List.of("")),
                Arguments.of("pobi,", List.of("pobi", "")),
                Arguments.of("pobi,,,", List.of("pobi", "", "", ""))
        );
    }
}
