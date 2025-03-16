package object.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class CardNumberTest {

    @ParameterizedTest
    @EnumSource(CardNumber.class)
    void 카드_번호_생성_확인(CardNumber cardNumber) {
        //then
        Assertions.assertThat(cardNumber).isInstanceOf(CardNumber.class);
    }
}
