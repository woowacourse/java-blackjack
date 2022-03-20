package blackjack.view.input.provider;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class InputViewTestProvider {

    public static Stream<Arguments> provideForRequestPlayerNamesTest() {
        return Stream.of(
                Arguments.of("pobi,if,sun", List.of("pobi", "if", "sun")),
                Arguments.of("pobi, if, sun", List.of("pobi", "if", "sun")),
                Arguments.of("", List.of("")),
                Arguments.of("    ", List.of("")),
                Arguments.of("pobi,", List.of("pobi", "")),
                Arguments.of("pobi,,,", List.of("pobi", "", "", ""))
        );
    }

    public static Stream<Arguments> provideForRequestDrawingCardChoiceTest() {
        return Stream.of(
                Arguments.of("y", true),
                Arguments.of("Y", true),
                Arguments.of("n", false),
                Arguments.of("n", false)
        );
    }

}
