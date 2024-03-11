package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Card(CardRank.EIGHT, CardShape.CLOVER))
                .doesNotThrowAnyException();
    }
}
