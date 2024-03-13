package domain;

import domain.constant.CardType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTypeTest {
    @Test
    @DisplayName("카드의 타입을 반환한다.")
    void getType() {
        Assertions.assertThat(CardType.CLOVER.getType())
                .isEqualTo("클로버");
    }
}
