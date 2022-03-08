package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    @DisplayName("카드를 추가할 떄 null을 전달하면 예외를 발생한다.")
    void thrownExceptionWhenGivenNull() {
        Cards cards = new Cards();
        Assertions.assertThatThrownBy(() -> cards.addCard(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바른 카드를 입력해주세요.");
    }

}
