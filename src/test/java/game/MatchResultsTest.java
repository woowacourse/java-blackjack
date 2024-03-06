package game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MatchResultsTest {

    @Test
    @DisplayName("이름으로 결과를 올바르게 가져온다.")
    void getResultByNameTest() {
        MatchResults matchResults = new MatchResults();
        matchResults.addResult("aru", 20, 21);
        assertThat(matchResults.getResultByName("aru")).isEqualTo(MatchResult.DEALER_WIN);
    }

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
