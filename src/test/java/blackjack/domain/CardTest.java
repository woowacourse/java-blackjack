package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CardTest {
    @DisplayName("카드 동일성 검증")
    @Test
    void createCard() {
        Shape diamond = Shape.DIAMOND;
        CardValue a = CardValue.ACE;
        Card diamondOne = new Card(diamond, a);
        assertThat(diamondOne).isEqualTo(new Card(diamond, a));
    }
}
