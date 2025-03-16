package domain.match;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchResultTest {

    @DisplayName("딜러와 플레이어의 점수가 같다면 draw를 반환한다.")
    @Test
    void draw() {
        //given
        int dealerScore = 10;
        int playerScore = 10;

        //when
        MatchResult actual = MatchResult.judge(dealerScore, playerScore);

        //then
        assertThat(actual).isEqualTo(MatchResult.DRAW);
    }

    @DisplayName("딜러와 플레이어중 점수가 딜러의 점수가 더 높다면 WIN을 반환한다.")
    @Test
    void win() {
        //given
        int dealerScore = 13;
        int playerScore = 10;

        //when
        MatchResult actual = MatchResult.judge(dealerScore, playerScore);

        //then
        assertThat(actual).isEqualTo(MatchResult.WIN);
    }

    @DisplayName("딜러와 플레이어중 점수가 플레이어의 점수가 더 높다면 LOSE를 반환한다.")
    @Test
    void lose() {
        //given
        int dealerScore = 8;
        int playerScore = 10;

        //when
        MatchResult actual = MatchResult.judge(dealerScore, playerScore);

        //then
        assertThat(actual).isEqualTo(MatchResult.LOSE);
    }

    @DisplayName("딜러가 승리하면 플레이어는 패배다.")
    @Test
    void dealerIsWinThanPlayerLose() {
        //given
        int dealerScore = 13;
        int playerScore = 10;

        //when
        MatchResult result = MatchResult.judge(dealerScore, playerScore);
        MatchResult actual = result.reverse();

        //then
        assertThat(actual).isEqualTo(MatchResult.LOSE);
    }

    @DisplayName("딜러가 패배하면 플레이어는 승리다.")
    @Test
    void dealerIsLoseThanPlayerWin() {
        //given
        int dealerScore = 8;
        int playerScore = 10;

        //when
        MatchResult result = MatchResult.judge(dealerScore, playerScore);
        MatchResult actual = result.reverse();

        //then
        assertThat(actual).isEqualTo(MatchResult.WIN);
    }

}
