package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("card 리스트를 입력했을 때 Cards객체가 정상적으로 생성됨")
    void makeCards() {
        List<Card> cards = List.of(new Card(Suit.CLOVER, Denomination.A),
            new Card(Suit.SPADE, Denomination.FIVE),
            new Card(Suit.HEART, Denomination.J));

        Assertions.assertDoesNotThrow(() -> new Cards(cards));
    }

    @Test
    @DisplayName("A카드를 가지고 있지 않을 때, 카드의 합이 정상적으로 계산된다")
    void getScoreWithNotA() {
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Denomination.THREE),
                new Card(Suit.HEART, Denomination.FOUR),
                new Card(Suit.SPADE, Denomination.FIVE)));

        assertThat(cards.getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("A카드를 가지고 있을 때, 카드의 합은 유리한 쪽(21에 인접한)으로 계산된다")
    void getScoreWithAUnder21() {
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Denomination.A),
                new Card(Suit.HEART, Denomination.FOUR),
                new Card(Suit.SPADE, Denomination.FIVE)));

        assertThat(cards.getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("A카드를 가지고 있을 때, 카드의 합은 유리한 쪽(21에 인접한)으로 계산된다")
    void getScoreWithAOver21() {
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Denomination.FIVE),
                new Card(Suit.HEART, Denomination.K),
                new Card(Suit.SPADE, Denomination.A)));

        assertThat(cards.getScore()).isEqualTo(16);
    }
    
    @Test
    @DisplayName("A카드를 여러장 갖고있을 때, ")
    void cardsWithManyA() {
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Denomination.FIVE),
                new Card(Suit.HEART, Denomination.A),
                new Card(Suit.SPADE, Denomination.A),
                new Card(Suit.CLOVER, Denomination.A)));

        assertThat(cards.getScore()).isEqualTo(18);
    }
}


