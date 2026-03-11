package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.CardShape;
import blackjack.domain.deck.CardValue;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void 카드가_올바르게_생성하는지에_대한_테스트() {
        // given
        CardValue value = CardValue.ACE;
        CardShape cardShape = CardShape.DIAMOND;

        // when
        Card card = new Card(value, cardShape);

        // then
        assertThat("A다이아몬드").isEqualTo(card.getName());
    }

}
