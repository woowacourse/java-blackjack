package domain;

import domain.game.ResultStatus;
import domain.money.Bet;
import domain.money.Profit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetTest {
    
    @Test
    @DisplayName("Bet 생성 테스트")
    void create() {
        Bet bet = new Bet(1000);
        Assertions.assertThat(bet.getBet()).isEqualTo(1000);
    }
    
    @Test
    @DisplayName("Bet 생성 예외 테스트 - 0보다 작은 값")
    void createException() {
        Assertions.assertThatThrownBy(() -> new Bet(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 0보다 커야 합니다.");
    }
    
    @Test
    @DisplayName("Bet 수익 계산 테스트")
    void calculateProfitFromResult() {
        Bet bet = new Bet(1000);
        Assertions.assertThat(bet.calculateProfitFromResult(ResultStatus.WIN)).isEqualTo(new Profit(1000));
        Assertions.assertThat(bet.calculateProfitFromResult(ResultStatus.WIN_BLACKJACK)).isEqualTo(new Profit(1500));
        Assertions.assertThat(bet.calculateProfitFromResult(ResultStatus.DRAW)).isEqualTo(new Profit(0));
        Assertions.assertThat(bet.calculateProfitFromResult(ResultStatus.LOSE)).isEqualTo(new Profit(-1000));
        
    }
}