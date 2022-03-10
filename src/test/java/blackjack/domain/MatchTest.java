package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MatchTest {

    @ParameterizedTest
    @CsvSource(value = {
            "21:20:WIN", "20:22:WIN",
            "21:21:DRAW",
            "22:20:LOSE", "20:21:LOSE", "23:22:LOSE", "23:23:LOSE", "23:24:LOSE"}
            , delimiter = ':')
    @DisplayName("게스트 승무패 결정 로직 확인")
    public void checkGuestFindWinner(int playerPoint, int dealerPoint, Match result) {
        assertThat(Match.findWinner(playerPoint, dealerPoint)).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"LOSE:WIN", "WIN:LOSE", "DRAW:DRAW"}, delimiter = ':')
    @DisplayName("딜러 승무패 결정 로직 확인")
    public void checkDealerFindWinner(Match guest, Match dealer) {
        assertThat(guest.getDealerResult()).isEqualTo(dealer);
    }
}
