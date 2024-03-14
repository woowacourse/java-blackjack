package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.ParticipantScoreStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinStatusTest {
    @DisplayName("딜러와 참여자의 점수로 승패를 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {                         // 딜러 | 참여자     | 참여자 승패
            "21, true, 20, false,LOSE",              // 블랙잭 , 생존    , 패
            "21, true, 22, false,LOSE",              // 블랙잭 , 죽음    , 패
            "20, false, 19,false, LOSE",              // 생존 , 생존     , 패
            "20, false, 22,false, LOSE",              // 생존 , 죽음      , 패
            "22, false, 22,false, LOSE",               // 죽음   , 죽음   , 패
            "23, false, 22,false, LOSE",               // 죽음   , 죽음   , 패
            "22, false, 23,false, LOSE",               // 죽음   , 죽음   , 패
            "19, false, 20,false, WIN",              // 생존 , 생존      , 승
            "22, false, 20,false, WIN",               // 죽음 , 생존      , 승
            "20, false, 21, true, WIN_BLACKJACK",     // 생존 , 블랙잭    , 블랙잭승
            "20, false, 21, false, WIN",             // 생존 , 21     , 승
            "22, false, 21,false, WIN",              // 죽음  , 21      , 승
            "21, true, 21, true, DRAW",              // 블랙잭 , 블랙잭   , 무
            "21, true, 21,false, DRAW",              // 블랙잭 , 21    , 패
            "21, false, 21,true, WIN_BLACKJACK",     // 21 , 블랙잭    , 블랙잭승
            "21, false, 21,false, DRAW",              // 21 , 21      , 무
            "20, false, 20,false, DRAW"})           // 생존 , 생존    , 무
    void of(int dealerScore, boolean isDealerBlackjack, int playerScore, boolean isPlayerBlackjack,
            WinStatus expected) {
        // given
        ParticipantScoreStatus dealerScoreStatus = new ParticipantScoreStatus(isDealerBlackjack, dealerScore);
        ParticipantScoreStatus playerScoreStatus = new ParticipantScoreStatus(isPlayerBlackjack, playerScore);

        // when
        WinStatus winStatus = WinStatus.of(dealerScoreStatus, playerScoreStatus);

        // then
        assertThat(winStatus).isEqualTo(expected);
    }
}