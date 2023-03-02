package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HoldingCardsTest {

    @Test
    @DisplayName("가진 카드의 합 테스트")
    void sum_of_cards() {
        HoldingCards holdingCards = new HoldingCards();
        Card card1 = new Card(Shape.DIAMOND, Number.KING);
        Card card2 = new Card(Shape.SPADE, Number.FOUR);
        holdingCards.initialCard(card1, card2);

        assertThat(holdingCards.sum()).isEqualTo(14);
    }
}
