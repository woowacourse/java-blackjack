import bank.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    void 돈_생성() {
        //given
        Money money = new Money("10000");

        //when & then
        Assertions.assertThat(money).isInstanceOf(Money.class);
    }

    @Test
    void 돈_숫자_검증() {
        //given
        String inputMoney = "1asbe";

        //when & then
        Assertions.assertThatThrownBy(() -> new Money(inputMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
