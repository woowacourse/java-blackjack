package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.judgement.BettingMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingMoneyTest {

    @Test
    @DisplayName("베팅 금액 정상 테스트")
    void 베팅_금액_정상_테스트() {
        assertDoesNotThrow(() ->  new BettingMoney("10000"));
    }

    @Test
    @DisplayName("배팅 금액 오류 테스트: 배팅 금액이 음수인 경우")
    void 배팅_금액이_음수인_경우() {
        assertThatThrownBy(() ->  new BettingMoney("-100"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("배팅 금액 오류 테스트: 배팅 금액이 정수가 아닌 경우")
    void 배팅_금액이_정수가_아닌_경우() {
        assertThatThrownBy(() ->  new BettingMoney("10.0"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
