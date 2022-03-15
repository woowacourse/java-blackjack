package blackjack.domain.machine;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HitTest {

    @ParameterizedTest
    @CsvSource(value = {"y:true", "n:false"}, delimiter = ':')
    @DisplayName("y와 n에 대한 응답 확인")
    void isYesTest(String response, boolean hit) {
        assertThat(Hit.isYes(response)).isEqualTo(hit);
    }
}
