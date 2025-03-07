import domain.CardNumber;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardNumberTest {
    @DisplayName("열 세개의 숫자 중 하나의 숫자를 뽑는다.")
    @Test
    void test() {
        // given
        int oneIndex = 13;

        // when
        CardNumber cardNumber = CardNumber.pick(oneIndex);

        // then
        Assertions.assertThat(cardNumber).isEqualTo(CardNumber.K);
    }
}
