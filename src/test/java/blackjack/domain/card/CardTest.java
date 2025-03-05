package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드를 생성한다.")
    @Test
    void test() {
        // given
        final Shape shape = Shape.DIAMOND;
        final Denomination denomination = Denomination.ACE;

        // when & then
        Assertions.assertThatCode(() -> new Card(shape, denomination))
                .doesNotThrowAnyException();
    }
}