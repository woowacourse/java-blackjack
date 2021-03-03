package blackjacktest.domaintest.cardtest;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardTest {

    @Test
    @DisplayName("카드 생성 성공")
    void createCard() {
        Card card = new Card(Shape.SPADE, Denomination.ACE);
        assertThat(card).isEqualTo(new Card(Shape.SPADE, Denomination.ACE));
    }
}
