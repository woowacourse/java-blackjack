package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NameTest {

    @ParameterizedTest
    @NullAndEmptySource
    void validate_BlankName_ExceptionThrown(String blank) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(blank));
    }
}
