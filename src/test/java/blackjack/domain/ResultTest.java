package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ResultTest {

    @ParameterizedTest
    @DisplayName("WIN과 LOSE를 바꿔서 return하는 지 테스트")
    @CsvSource(value = {"WIN:LOSE", "LOSE:WIN", "DRAW:DRAW"}, delimiter = ':')
    public void replaceWinWithLose(Result input, Result output) {
        assertThat(input.replaceWinWithLose()).isEqualTo(output);
    }
}
