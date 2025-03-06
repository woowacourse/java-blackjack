package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CommandTest {

    @DisplayName("입력에 대한 커맨드가 반환된다.")
    @ParameterizedTest
    @CsvSource(value = {
            "Y, YES", "N, NO"
    })
    void 입력에_대한_커맨드가_반환된다(String input, Command command) {

        // given

        //when & then
        assertThat(Command.find(input)).isEqualTo(command);
    }
}
