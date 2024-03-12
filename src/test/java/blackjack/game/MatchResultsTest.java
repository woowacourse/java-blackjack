package blackjack.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchResultsTest {

    @Test
    @DisplayName("이름이 존재하지 않는 경우 예외를 발생시킨다.")
    void nameNotFoundTest() {
        // given
        MatchResults matchResults = new MatchResults();
        // when, then
        assertThatThrownBy(() -> matchResults.getResultByName("pobi"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 이름입니다.");
    }
}
