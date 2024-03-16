package view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameCommandTest {

    @DisplayName("카드를 받는다는 표현에 따른 boolean 값을 return 한다.")
    @ParameterizedTest
    @CsvSource({
            "Y, false",
            "N, true"
    })
    void getCardCommand(String command, boolean expectedReturn) {
        Assertions.assertThat(GameCommand.isStandCommand(command)).isEqualTo(expectedReturn);
    }
}
