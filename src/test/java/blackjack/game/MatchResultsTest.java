package blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("원하는 결과의 개수를 구한다.")
    void getDesiredResultCountTest() {
        // given
        MatchResults matchResults = new MatchResults();
        matchResults.addResult("aru", 10, 20);
        matchResults.addResult("pobi", 10, 20);
        matchResults.addResult("atto", 10, 10);
        matchResults.addResult("jazz", 20, 10);
        // when
        int playerWinCount = matchResults.getResultCount(MatchResult.PLAYER_WIN);
        int tieCount = matchResults.getResultCount(MatchResult.TIE);
        int dealerWinCount = matchResults.getResultCount(MatchResult.DEALER_WIN);
        // then
        assertAll(
                () -> assertThat(playerWinCount).isEqualTo(1),
                () -> assertThat(tieCount).isEqualTo(1),
                () -> assertThat(dealerWinCount).isEqualTo(2)
        );
    }
}
