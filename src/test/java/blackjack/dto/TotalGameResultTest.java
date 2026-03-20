package blackjack.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.MatchResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalGameResultTest {

    @Test
    @DisplayName("최종 게임 결과 dto의 생성을 확인한다.")
    void makeTotalGameResult() {
        // given
        DealerGameResult dealerGameResult = DealerGameResult.from(10000);
        PlayerGameResult losingResult = PlayerGameResult.of("boye", MatchResult.LOSE, -10000);
        PlayerGameResult tieResult = PlayerGameResult.of("sumin", MatchResult.TIE, 0);

        // when
        TotalGameResult totalGameResult = TotalGameResult.of(
            dealerGameResult,
            List.of(losingResult, tieResult)
        );

        // then
        assertAll(
            () -> assertThat(totalGameResult.dealerGameResult()).isEqualTo(dealerGameResult),
            () -> assertThat(totalGameResult.playerGameResult())
                .containsExactly(losingResult, tieResult)
        );
    }
}
