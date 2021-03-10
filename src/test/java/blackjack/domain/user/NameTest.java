package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class NameTest {

    @ParameterizedTest
    @CsvSource(value = {"choonsik  :choonsik", "  pobi:pobi", "   jason:jason"}, delimiter = ':')
    @DisplayName("이름 양옆의 공백은 제거된다.")
    void create(String input, String expected) {
        Name name = new Name(input);
        assertThat(name).isEqualTo(new Name(expected));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이름은 비어있으면 안 된다.")
    void invalidCreation(String value) {
        assertThatThrownBy(() -> {
            Name name = new Name(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
