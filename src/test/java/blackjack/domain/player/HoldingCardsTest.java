package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HoldingCardsTest {

    private HoldingCards holdingCards;

    @BeforeEach
    void setup() {
        holdingCards = new HoldingCards();
    }

    @Test
    @DisplayName("가진 카드의 합 테스트")
    void sum_of_cards() {
        Card card1 = new Card(Shape.DIAMOND, Number.KING);
        Card card2 = new Card(Shape.SPADE, Number.FOUR);
        holdingCards.initialCard(card1, card2);

        assertThat(holdingCards.getSum().getValue()).isEqualTo(14);
    }

    @Test
    @DisplayName("ACE가 존재하는 경우 21에 가장 가까운 합을 찾는지 확인하는 테스트")
    void find_sum_of_nearly_21() {
        Card cloverAce = new Card(Shape.CLOVER, Number.ACE);
        Card cloverFive = new Card(Shape.CLOVER, Number.FIVE);
        Card spadeAce = new Card(Shape.SPADE, Number.ACE);
        holdingCards.initialCard(cloverAce, cloverFive);
        holdingCards.add(spadeAce);

        assertThat(holdingCards.getSum().getValue())
                .isEqualTo(17);
    }
}
