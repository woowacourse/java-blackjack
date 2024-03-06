package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinStatusTest {

    @ParameterizedTest
    @DisplayName("딜러와 참여자의 점수로 승패를 계산한다.")
    @CsvSource(value = {        // 딜러 | 참여자 | 참여자 승패
            "20, 21, WIN",     // 생존 , 블랙잭 , 승
            "22, 21, WIN",      // 죽음  , 블랙잭 , 승
            "22, 20, WIN",      // 죽음 , 생존   , 승
            "19, 20, WIN",     // 생존 , 생존  , 승
            "21, 20, LOSE",     // 블랙잭 , 생존  , 패
            "21, 22, LOSE",     // 블랙잭 , 죽음  , 패
            "20, 19, LOSE",     // 생존 , 생존  , 패
            "20, 22, LOSE",     // 생존 , 죽음  , 패
            "22, 22, LOSE",      // 죽음   , 죽음   , 패
            "23, 22, LOSE",      // 죽음   , 죽음   , 패
            "22, 23, LOSE",      // 죽음   , 죽음   , 패
            "21, 21, DRAW",     // 블랙잭 , 블랙잭 , 무
            "20, 20, DRAW"})     // 생존 , 생존  , 무
    void of(int rawDealerScore, int rawPlayerScore, WinStatus expected) {
        // given
        Score dealerScore = new Score(rawDealerScore);
        Score playerScore = new Score(rawPlayerScore);

        // when
        WinStatus winStatus = WinStatus.of(dealerScore, playerScore);

        // then
        assertThat(winStatus).isEqualTo(expected);
    }
}