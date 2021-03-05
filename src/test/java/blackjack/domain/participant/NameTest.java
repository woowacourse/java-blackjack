package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @Test
    @DisplayName("이름 생성된다.")
    void testCreateName() {
        String nameString = "pobi";
        Name name = new Name(nameString);
        assertThat(name).isEqualTo(new Name(nameString));
    }

    @ParameterizedTest
    @NullSource
    void testValidateNull(String name) {
        assertThatThrownBy(() -> new Name(name)).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void testCreateName(String name) {
        assertThatThrownBy(() -> new Name(name)).isInstanceOf(IllegalArgumentException.class);
    }

}
