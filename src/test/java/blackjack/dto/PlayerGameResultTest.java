package blackjack.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.MatchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerGameResultTest {

    @Test
    @DisplayName("게임 결과 dto 생성을 확인한다.")
    void makePlayerGameResult() {
        // given
        String nickname = "boye";
        MatchResult matchResult = MatchResult.WIN;
        long profit = 1000;

        // when
        PlayerGameResult playerGameResult = PlayerGameResult.of(nickname, matchResult, profit);

        // then
        assertAll(
            () -> assertThat(playerGameResult.nickname()).isEqualTo("boye"),
            () -> assertThat(playerGameResult.matchResult()).isEqualTo(MatchResult.WIN),
            () -> assertThat(playerGameResult.profit()).isEqualTo(1000)
        );
    }
}
