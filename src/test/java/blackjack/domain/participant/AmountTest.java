package blackjack.domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {

    @Test
    void validateZero() {
        Assertions.assertThatThrownBy(() -> new Amount(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1원 이상의 배팅금액을 입력해주세요.");
    }

}