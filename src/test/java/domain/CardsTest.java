package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Number;
import domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("card 리스트를 입력했을 때 Cards객체가 정상적으로 생성됨")
    void makeCards() {
        List<Card> cards = List.of(new Card(Suit.CLOVER, Number.A),
            new Card(Suit.SPADE, Number.FIVE),
            new Card(Suit.HEART, Number.J));

        Assertions.assertDoesNotThrow(() -> new Cards(cards));
    }

    @Test
    @DisplayName("A카드를 가지고 있지 않을 때, 카드의 합이 정상적으로 계산된다")
    void getScoreWithNotA() {
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Number.THREE),
                new Card(Suit.HEART, Number.FOUR),
                new Card(Suit.SPADE, Number.FIVE)));

        assertThat(cards.getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("A카드를 가지고 있을 때, 카드의 합은 유리한 쪽(21에 인접한)으로 계산된다")
    void getScoreWithAUnder21() {
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Number.A),
                new Card(Suit.HEART, Number.FOUR),
                new Card(Suit.SPADE, Number.FIVE)));

        assertThat(cards.getScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("A카드를 가지고 있을 때, 카드의 합은 유리한 쪽(21에 인접한)으로 계산된다")
    void getScoreWithAOver21() {
        Cards cards = new Cards(
            List.of(new Card(Suit.CLOVER, Number.FIVE),
                new Card(Suit.HEART, Number.K),
                new Card(Suit.SPADE, Number.A)));

        assertThat(cards.getScore()).isEqualTo(16);
    }
}


