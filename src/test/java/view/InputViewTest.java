package view;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class InputViewTest {
    @ParameterizedTest
    @DisplayName("참여할 사람의 이름 중복검사")
    @CsvSource(value = {"apple,banana:true", "apple,banana,apple:false"}, delimiter = ':')
    void checkDuplication(String string, boolean expected) {
        assertThat(InputView.hasNoDuplication(string)).isEqualTo(expected);
    }
}
