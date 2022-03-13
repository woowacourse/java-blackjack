package blackJack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinOrLoseTest {

    @ParameterizedTest(name = "주어진 점수가 burst인지 확인 테스트")
    @CsvSource(value = {"20,false", "21,false", "22,true"})
    void isBurst(int score, boolean expected) {
        assertThat(WinOrLose.isBurst(score)).isEqualTo(expected);
    }
}
