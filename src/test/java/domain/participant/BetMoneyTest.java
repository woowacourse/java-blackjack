package domain.participant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BetMoneyTest {
    private static final double BLACKJACK = 1.5;
    private static final double WIN = 1;
    private static final double DRAW = 0;
    private static final double LOSE = -1;

    @Test
    void 블랙잭이면_정해진_배율이_곱해져야_한다() {
        BetMoney betMoney = BetMoney.valueOf("100");

        BetMoney afterBlackjack = betMoney.multiply(BLACKJACK);
        Assertions.assertThat(afterBlackjack).isEqualTo(BetMoney.valueOf("150"));
    }

    @Test
    void 승리_시_정해진_배율이_곱해져야_한다() {
        BetMoney betMoney = BetMoney.valueOf("100");

        BetMoney afterBlackjack = betMoney.multiply(WIN);
        Assertions.assertThat(afterBlackjack).isEqualTo(BetMoney.valueOf("100"));
    }

    @Test
    void 무승부_시_정해진_배율이_곱해져야_한다() {
        BetMoney betMoney = BetMoney.valueOf("100");

        BetMoney afterBlackjack = betMoney.multiply(DRAW);
        Assertions.assertThat(afterBlackjack).isEqualTo(BetMoney.valueOf("0"));
    }

    @Test
    void 패배_시_정해진_배율이_곱해져야_한다() {
        BetMoney betMoney = BetMoney.valueOf("100");

        BetMoney afterBlackjack = betMoney.multiply(LOSE);
        Assertions.assertThat(afterBlackjack).isEqualTo(BetMoney.valueOf("-100"));
    }

    @Test
    void 블랙잭인_경우_출력_시_소수점은_버려진다() {
        BetMoney betMoney = BetMoney.valueOf("5");

        BetMoney afterBlackjack = betMoney.multiply(1.5);
        Assertions.assertThat(afterBlackjack.getValue().setScale(0, RoundingMode.DOWN))
                .isEqualTo(BigDecimal.valueOf(7));
    }

    @Test
    void 소수점_출력_시_소수점_정책을_적용하지_않으면_예외가_발생한다() {
        BetMoney betMoney = BetMoney.valueOf("101");

        BetMoney afterBlackjack = betMoney.multiply(BLACKJACK);
        Assertions.assertThatThrownBy(() -> afterBlackjack.getValue().setScale(0))
                .isInstanceOf(ArithmeticException.class);
    }

    @Test
    void 소수점_출력_시_소수점_정책을_적용하면_않으면_예외가_발생하지_않는다() {
        BetMoney betMoney = BetMoney.valueOf("101");

        BetMoney afterBlackjack = betMoney.multiply(BLACKJACK);
        Assertions.assertThatNoException().isThrownBy(() -> afterBlackjack.getValue().setScale(0, RoundingMode.DOWN));
    }
}
