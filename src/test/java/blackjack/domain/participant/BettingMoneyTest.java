package blackjack.domain.participant;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BettingMoneyTest {
    
    @Test
    @DisplayName("베팅 머니 생성 테스트")
    void initTest() {
        
        // given
        String input = "1500";
        
        // when
        BettingMoney bettingMoney = BettingMoney.from(input);
        
        // then
        assertThat(bettingMoney.getMoney()).isEqualTo(1500);
    }
    
    @Test
    @DisplayName("입력값이 숫자가 아닐 경우 예외 발생")
    void initTest_IsNotDigit_ExceptionThrown() {
        
        // given
        String input = "1500$";
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> BettingMoney.from(input);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("숫자만 입력해주세요");
    }
}