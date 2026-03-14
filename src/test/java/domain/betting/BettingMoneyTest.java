package domain.betting;

import domain.betting.exception.BettingException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BettingMoneyTest {

    @Test
    void 베팅금액은_음수일_수_없다() {
        String negativeNumber = "-1000";

        Assertions.assertThatThrownBy(() -> {
            BettingMoney.bet(negativeNumber);
        }).isInstanceOf(BettingException.class);
    }

    @Test
    void 베팅금액은_문자일_수_없다() {
        String negativeNumber = "베팅금액은 문자일 수 없다.";

        Assertions.assertThatThrownBy(() -> {
            BettingMoney.bet(negativeNumber);
        }).isInstanceOf(BettingException.class);
    }

}
