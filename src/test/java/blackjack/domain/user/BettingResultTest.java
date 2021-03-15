package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BettingResultTest {

    @DisplayName("블랙잭인 경우 수익 : 1.5배")
    @Test
    void getEarningMoney_blackJack() {
        BettingResult bettingResult = new BettingResult("A", MatchResult.WIN_BLACKJACK.calculateEarningMoney(20000));
        assertThat(bettingResult.getEarningMoney()).isEqualTo(30000);
    }

    @DisplayName("이긴 경우 수익 : 1배")
    @Test
    void getEarningMoney_win() {
        BettingResult bettingResult = new BettingResult("A", MatchResult.WIN_NORMAL.calculateEarningMoney(20000));
        assertThat(bettingResult.getEarningMoney()).isEqualTo(20000);
    }

    @DisplayName("비긴 경우 수익 : 수익 없음")
    @Test
    void getEarningMoney_draw() {
        BettingResult bettingResult = new BettingResult("A", MatchResult.DRAW.calculateEarningMoney(20000));
        assertThat(bettingResult.getEarningMoney()).isEqualTo(0);
    }

    @DisplayName("진 경우 수익 : 마이너스")
    @Test
    void getEarningMoney_lose() {
        BettingResult bettingResult = new BettingResult("A", MatchResult.LOSE.calculateEarningMoney(20000));
        assertThat(bettingResult.getEarningMoney()).isEqualTo(-20000);
    }
}