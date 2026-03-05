package blackjack.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SuitTest {

    @Test
    void 스페이드_하트_다이아몬드_클로버를_제외한_다른_문자가_들어오면_예외가_처리한다() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Suit.of("아무값");
        });
    }
}
