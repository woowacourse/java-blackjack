package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RuleTest {
    @DisplayName("에이스를 고려해서 총합을 조정한다")
    @CsvSource(value = {"18,0,18", "23,1,13", "21,1,21", "18,1,18", "24,2,14", "22,2,12"})
    @ParameterizedTest
    void adjustSumByAceTest(int sum, int aceCount, int expected) {
        assertThat(Rule.adjustSumByAce(sum, aceCount)).isEqualTo(expected);
    }
}
