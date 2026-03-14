package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BetMoneyTest {
    @Test
    void 블랙잭이면_정해진_배율이_곱해져야_한다() {
        BetMoney betMoney = BetMoney.valueOf(100);

        BetMoney afterBlackjack = betMoney.blackjack();
        Assertions.assertThat(afterBlackjack).isEqualTo(BetMoney.valueOf(150.0));
    }

    @Test
    void 승리_시_정해진_배율이_곱해져야_한다() {
        BetMoney betMoney = BetMoney.valueOf(100);

        BetMoney afterBlackjack = betMoney.win();
        Assertions.assertThat(afterBlackjack).isEqualTo(BetMoney.valueOf(100.0));
    }

    @Test
    void 무승부_시_정해진_배율이_곱해져야_한다() {
        BetMoney betMoney = BetMoney.valueOf(100);

        BetMoney afterBlackjack = betMoney.draw();
        Assertions.assertThat(afterBlackjack).isEqualTo(BetMoney.valueOf(0));
    }

    @Test
    void 패배_시_정해진_배율이_곱해져야_한다() {
        BetMoney betMoney = BetMoney.valueOf(100);

        BetMoney afterBlackjack = betMoney.lose();
        Assertions.assertThat(afterBlackjack).isEqualTo(BetMoney.valueOf(-100));
    }
}
