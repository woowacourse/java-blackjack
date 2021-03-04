package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameTest {
    private static Stream<String> provideInvalidName() {
        return Stream.of("", " ", null);
    }

    @ParameterizedTest
    @DisplayName("invalid 한 input 값 익셉션 확인")
    @MethodSource("provideInvalidName")
    void blankName(String input) {
        assertThatThrownBy(() -> new Name(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Name toString 확인")
    void nameMatch() {
        String nameStr = "tom";
        Name name = new Name(nameStr);
        assertThat(name.toString()).isEqualTo(nameStr);
    }
}