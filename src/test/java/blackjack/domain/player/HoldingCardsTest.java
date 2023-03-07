package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
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
        Card DiamondKing = new Card(Shape.DIAMOND, Symbol.KING);
        Card SpadeFour = new Card(Shape.SPADE, Symbol.FOUR);

        holdingCards.initialize(DiamondKing, SpadeFour);

        assertThat(holdingCards.getSum()).isEqualTo(14);
    }

    @Test
    @DisplayName("합이 큰 경우 테스트")
    void big_sum() {
        Card diamondKing = new Card(Shape.DIAMOND, Symbol.KING);
        Card heartKIng = new Card(Shape.HEART, Symbol.KING);
        Card SpadeFour = new Card(Shape.SPADE, Symbol.FOUR);

        holdingCards.initialize(diamondKing, heartKIng);
        holdingCards.add(SpadeFour);

        assertThat(holdingCards.getSum()).isEqualTo(24);
    }

    @Test
    @DisplayName("ACE가 존재하는 경우 21에 가장 가까운 합을 찾는지 확인하는 테스트")
    void find_sum_of_nearly_21() {
        Card cloverAce = new Card(Shape.CLOVER, Symbol.ACE);
        Card cloverFive = new Card(Shape.CLOVER, Symbol.FIVE);
        Card spadeAce = new Card(Shape.SPADE, Symbol.ACE);
        Card heartAce = new Card(Shape.HEART, Symbol.ACE);

        holdingCards.initialize(cloverAce, cloverFive);
        holdingCards.add(spadeAce);
        holdingCards.add(heartAce);

        assertThat(holdingCards.getSum()).isEqualTo(18);
    }
}
