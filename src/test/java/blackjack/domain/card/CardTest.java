package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드를 생성한다.")
    @Test
    void createCard() {
        // given
        final Shape shape = Shape.DIAMOND;
        final CardScore cardScore = CardScore.A;

        // when & then
        Assertions.assertThatCode(() -> new Card(shape, cardScore))
                .doesNotThrowAnyException();
    }
}
