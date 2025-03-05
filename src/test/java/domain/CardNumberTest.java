package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;

public class CardNumberTest {

    @Test
    @EnumSource(CardNumber.class)
    void 카드_넘버_생성_확인(CardNumber cardNumber) {
        //then
        Assertions.assertThat(cardNumber).isInstanceOf(CardNumber.class);
    }
}
