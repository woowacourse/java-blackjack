package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BettingMoneyTest {

    @Nested
    class 생성 {
        @Test
        void should_예외를던진다_when_배팅금액이0원이하일시() {
            //given
            int betMoney = 0;

            //when
            ThrowingCallable throwingCallable = () -> new BettingMoney(betMoney);

            //then
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("베팅 금액은 0원보다 커야합니다.");
        }

        @Test
        void should_예외를던진다_when_배팅금액이1000원단위가아닐시() {
            //given
            int betMoney = 1400;

            //when
            ThrowingCallable throwingCallable = () -> new BettingMoney(betMoney);

            //then
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("베팅은 1000원 단위로 가능합니다.");
        }

        @Test
        void should_입력된베팅금을가진다_when_예외가아닌값이입력될시() {
            //given
            int betMoney = 10000;

            //when
            BettingMoney bettingMoney = new BettingMoney(betMoney);

            //then
            assertThat(bettingMoney.value()).isEqualTo(10000);
        }

    }

}
