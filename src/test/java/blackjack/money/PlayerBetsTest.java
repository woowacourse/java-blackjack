package blackjack.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.resultstate.MatchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBetsTest {

    @Test
    @DisplayName("배팅 정보를 바탕으로 수익을 올바르게 계산한다.")
    void calculateProfitTest() {
        // given
        BetTable betTable = new BetTable();
        betTable.placeBet("aru", Money.of(1_000));
        // when
        Money profit = betTable.calculateProfitByName("aru", MatchResult.TIE);
        // then
        assertThat(profit).isEqualTo(Money.zero());
    }

    @Test
    @DisplayName("배팅 정보가 존재하지 않을 경우 예외를 발생시킨다.")
    void betNotFoundTest() {
        // given
        PlayerBets playerBets = new PlayerBets();
        // when, then
        assertThatThrownBy(() -> playerBets.calculateProfitByName("aru", MatchResult.TIE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 정보가 존재하지 않습니다.");
    }
}
