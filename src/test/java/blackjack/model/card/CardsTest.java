package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {
    @Test
    @DisplayName("점수 계산은 카드 숫자를 기본으로 한다")
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
    @DisplayName("점수 계산 시 버스트가 아니면 Ace는 11점으로 계산한다")
    void calculateCardsTotalWithAceWhenNotBustTest() {
        // given
        Cards cards = new Cards();
        cards.addCards(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.ACE)
        ));

        // when
        int actualTotal = cards.calculateScore();

        // then
        assertThat(actualTotal).isEqualTo(21);
    }

    @Test
    @DisplayName("점수 계산 시 버스트면 Ace는 1점으로 계산한다")
    void calculateCardsTotalWithAceWhenBustTest() {
        // given
        Cards cards = new Cards();
        cards.addCards(List.of(
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.KING),
                new Card(Suit.HEART, Denomination.ACE)
        ));

        // when
        int actualTotal = cards.calculateScore();

        // then
        assertThat(actualTotal).isEqualTo(21);
    }

    @Test
    @DisplayName("점수가 21점을 넘으면 Blackjack 이다")
    void checkBlackjackTest() {
        // given
        Cards cards = new Cards();
        cards.addCards(List.of(
                new Card(Suit.HEART, Denomination.JACK),
                new Card(Suit.HEART, Denomination.ACE)
        ));

        // when & then
        assertThat(cards.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("카드를 추가할 수 있다")
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
