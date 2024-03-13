package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {
    @Test
    @DisplayName("딜러와 참여자들에게 카드를 2장씩 나누어 준다")
    void dealTest() {
        // when
        Cards cards = new Cards();
        cards.addCards(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        ));

        // then
        assertThat(cards.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("카드 합 계산은 카드 숫자를 기본으로 한다")
    void calculateCardsTotalTest() {
        // given
        Cards cards = new Cards();
        cards.addCards(List.of(
                new Card(Suit.HEART, Denomination.THREE),
                new Card(Suit.HEART, Denomination.THREE),
                new Card(Suit.HEART, Denomination.QUEEN)
        ));

        // when & then
        assertThat(cards.calculateScore()).isEqualTo(16);
    }

    @Test
    @DisplayName("Ace는 1 또는 11로 계산할 수 있다")
    void calculateCardsTotalWithAceTest() {
        // given
        Cards cards = new Cards();
        cards.addCards(List.of(
                new Card(Suit.HEART, Denomination.THREE),
                new Card(Suit.HEART, Denomination.THREE),
                new Card(Suit.HEART, Denomination.FOUR),
                new Card(Suit.HEART, Denomination.ACE)
        ));

        // when
        int actualTotal = cards.calculateScore();

        // then
        assertThat(actualTotal).isEqualTo(21);
    }

    @Test
    @DisplayName("BlackJack인지 확인한다")
    void checkBlackJackTest() {
        // given
        Cards cards = new Cards();
        cards.addCards(List.of(
                new Card(Suit.HEART, Denomination.JACK),
                new Card(Suit.HEART, Denomination.ACE)
        ));

        // when & then
        assertThat(cards.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("카드를 뽑는다")
    void addCardTest() {
        // when
        Cards cards = new Cards();
        cards.addCards(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        ));

        // when
        cards.addCard(new Card(Suit.HEART, Denomination.TWO));

        // then
        assertThat(cards.getCards()).hasSize(3);
    }
}
