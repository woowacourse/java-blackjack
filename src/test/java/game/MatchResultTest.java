package game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MatchResultTest {

    @Test
    @DisplayName("플레이어의 점수가 21점을 초과하면 딜러가 승리한다.")
    void playerBurstTest() {
        // when
        MatchResult result = MatchResult.chooseWinner(22, 22);
        // then
        assertThat(result).isEqualTo(MatchResult.DEALER_WIN);
    }

    @Test
    @DisplayName("플레이어의 점수가 21점 이하이고, 딜러의 점수가 21점을 초과하면 플레이어가 승리한다.")
    void dealerBurstTest() {
        // when
        MatchResult result = MatchResult.chooseWinner(20, 22);
        // then
        assertThat(result).isEqualTo(MatchResult.PLAYER_WIN);
    }

    @Test
    @DisplayName("양 쪽 모두 21점 이하일 때, 점수가 높은 사람이 승리한다.")
    void comparingScoreTest() {
        // when
        MatchResult expectPlayerWin = MatchResult.chooseWinner(21, 20);
        MatchResult expectTie = MatchResult.chooseWinner(20, 20);
        MatchResult expectDealerWin = MatchResult.chooseWinner(20, 21);
        // then
        assertAll(
                () -> assertThat(expectPlayerWin).isEqualTo(MatchResult.PLAYER_WIN),
                () -> assertThat(expectTie).isEqualTo(MatchResult.TIE),
                () -> assertThat(expectDealerWin).isEqualTo(MatchResult.DEALER_WIN)
        );
    }
}