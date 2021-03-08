package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.user.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class NameTest {

    @ParameterizedTest
    @CsvSource({"choonsik ", "  pobi", "jason", "ho dol"})
    @DisplayName("이름이 같으면 동일 인물로 본다.")
    void create(String value) {
        Name name = new Name(value);
        assertThat(name).isEqualTo(new Name(value));
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
