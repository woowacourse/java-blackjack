package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.participant.Name;
import org.junit.jupiter.api.Test;

class NameTest {
    @Test
    void 이름이_5자를_초과하면_예외를_발생한다() {
        assertThatThrownBy(() -> new Name("pobiss"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
