package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class CardTest {

    @Test
    @DisplayName("캐싱 및 동일성 테스트")
    void caching() {
        assertThat(Card.valueOf(Shape.DIAMOND, CardValue.ACE))
            .isSameAs((Card.valueOf(Shape.DIAMOND, CardValue.ACE)));
    }
}
