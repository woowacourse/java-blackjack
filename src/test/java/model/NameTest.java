package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {
    
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = " ")
    void 블랙잭_참여자_이름은_null이거나_공백일_수_없다(String invalidName) {
        // when & then
        assertThatThrownBy(() -> new Name(invalidName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 11})
    void 블랙잭_참여자_이름은_두자_미만이거나_10자를_초과할_수_없다(int repeatCount) {
        // given
        String invalidName = "1".repeat(repeatCount);

        // when & then
        assertThatThrownBy(() -> new Name(invalidName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 10})
    void 블랙잭_참여자_이름이_두자에서_10자라면_유효하다(int repeatCount) {
        // given
        String validName = "1".repeat(repeatCount);

        // when
        Name name = new Name(validName);

        // then
        assertThat(name.name()).isEqualTo(validName);
    }
}