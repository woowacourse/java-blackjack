package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("card 리스트를 입력했을 때 Cards객체가 정상적으로 생성됨")
    void makeCards() {
        List<Card> cards = List.of(Card.of(Suit.CLOVER, Denomination.A),
            Card.of(Suit.SPADE, Denomination.FIVE),
            Card.of(Suit.HEART, Denomination.J));

        Assertions.assertDoesNotThrow(() -> new Cards(cards));
    }

    @Test
    @DisplayName("A카드를 가지고 있지 않을 때, 카드의 합이 정상적으로 계산된다")
    void getScoreWithNotA() {
        Cards cards = new Cards(
            List.of(Card.of(Suit.CLOVER, Denomination.THREE),
                Card.of(Suit.HEART, Denomination.FOUR),
                Card.of(Suit.SPADE, Denomination.FIVE)));

        assertThat(cards.getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("A카드를 가지고 있을 때, 카드의 합은 유리한 쪽(21에 인접한)으로 계산된다")
    void getScoreWithAUnder21() {
        Cards cards = new Cards(
            List.of(Card.of(Suit.CLOVER, Denomination.A),
                Card.of(Suit.HEART, Denomination.FOUR),
                Card.of(Suit.SPADE, Denomination.FIVE)));

        assertThat(cards.getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("A카드를 가지고 있을 때, 카드의 합은 유리한 쪽(21에 인접한)으로 계산된다")
    void getScoreWithAOver21() {
        Cards cards = new Cards(
            List.of(Card.of(Suit.CLOVER, Denomination.FIVE),
                Card.of(Suit.HEART, Denomination.K),
                Card.of(Suit.SPADE, Denomination.A)));

        assertThat(cards.getScore()).isEqualTo(16);
    }

    @Test
    @DisplayName("A카드를 여러장 갖고있을 때, ")
    void cardsWithManyA() {
        Cards cards = new Cards(
            List.of(Card.of(Suit.CLOVER, Denomination.FIVE),
                Card.of(Suit.HEART, Denomination.A),
                Card.of(Suit.SPADE, Denomination.A),
                Card.of(Suit.CLOVER, Denomination.A)));

        assertThat(cards.getScore()).isEqualTo(18);
    }
}


