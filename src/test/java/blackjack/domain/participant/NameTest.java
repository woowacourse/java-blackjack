package blackjack.domain.participant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @Test
    void 플레이어의_이름을_알_수_있다() {
        final Name name = new Name("pobi");
        assertThat(name).isEqualTo(new Name("pobi"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "123456"})
    void 플레이어_이름의_길이가_범위를_벗어나면_예외가_발생한다(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
