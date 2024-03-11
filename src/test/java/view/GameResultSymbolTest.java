package view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultSymbolTest {

    @DisplayName("boolean 값에 따른 Symbol을 가져온다.")
    @ParameterizedTest
    @CsvSource({
            "true, 승",
            "false, 패"
    })
    void getResultSymbol(boolean isWins, String expectedSymbol) {
        Assertions.assertThat(GameResultSymbol.changeToSymbol(isWins).symbolName).isEqualTo(expectedSymbol);
    }

}
