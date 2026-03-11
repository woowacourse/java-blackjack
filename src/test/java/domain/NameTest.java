package domain;

import static domain.Name.MAXIMUM_BOUND;
import static domain.Name.MINIMUM_BOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("플레이어 이름은 2자 이상 10자 이하여야 한다.")
    public void 플레이어_이름_생성_성공() {
        // given
        final String tooShort = "a".repeat(MINIMUM_BOUND - 1);
        final String minLength = "a".repeat(MINIMUM_BOUND);
        final String validLength = "a".repeat((MINIMUM_BOUND + MAXIMUM_BOUND) / 2);
        final String maxLength = "a".repeat(MAXIMUM_BOUND);
        final String tooLong = "a".repeat(MAXIMUM_BOUND + 1);

        // then
        assertThatThrownBy(() -> new Name(tooShort))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(new Name(minLength))
                .isInstanceOf(Name.class);
        assertThat(new Name(validLength))
                .isInstanceOf(Name.class);
        assertThat(new Name(maxLength))
                .isInstanceOf(Name.class);
        assertThatThrownBy(() -> new Name(tooLong))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
