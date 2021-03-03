package blackjack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameTest {
    private static Stream<String> provideInvalidName() {
        return Stream.of("", " ", null);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidName")
    void blankName(String input) {
        assertThatThrownBy(() -> new Name(input)).isInstanceOf(IllegalArgumentException.class);
    }
}