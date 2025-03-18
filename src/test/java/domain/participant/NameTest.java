package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NameTest {

    @Nested
    class InvalidCases {

        @ParameterizedTest
        @CsvSource(value = {
                "null",
                "' '"},
                nullValues = {"null"})
        @DisplayName("이름은 텍스트를 가져야 한다.")
        void validateNotNull(String text) {
            // when & then
            assertThatThrownBy(() -> new Name(text))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 텍스트를 가져야 합니다.");
        }
    }
}
