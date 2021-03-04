package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CardTest {

    @DisplayName("캐싱 및 동일성 테스트")
    @Test
    void caching() {
        assertThat(Card.valueOf(Shape.DIAMOND, CardValue.ACE))
                .isSameAs((Card.valueOf(Shape.DIAMOND, CardValue.ACE)));
    }
}
