package blakjack.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChipTest {
    @ParameterizedTest
    @ValueSource(ints = {999, 1001})
    void InvalidNumber(final int invalidNumber) {
        assertThatThrownBy(() -> new Chip(invalidNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
