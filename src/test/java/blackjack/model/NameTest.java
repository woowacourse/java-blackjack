package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t", "   "})
    void 빈_값일_경우_예외가_발생한다(String emptyName) {
        assertThatThrownBy(() -> new Name(emptyName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void null_값인_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new Name(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {" jun ", " pobi", "jason "})
    void 앞뒤_공백을_제거한_값으로_저장한다(String untrimmedName) {
        Name name = new Name(untrimmedName);

        String expectedNameValue = untrimmedName.trim();
        String actualNameValue = name.get();

        assertThat(actualNameValue).isEqualTo(expectedNameValue);
    }
}
