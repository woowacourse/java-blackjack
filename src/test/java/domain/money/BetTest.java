package domain.money;

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
    @DisplayName("Bet 생성 예외 테스트 - 100의 배수가 아닌 값")
    void createException2() {
        Assertions.assertThatThrownBy(() -> new Bet(1001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 100원 단위로 입력해주세요.");
    }
    
}