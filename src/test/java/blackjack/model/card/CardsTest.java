package blackjack.model.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {
    @Test
    @DisplayName("딜러와 참여자들에게 카드를 2장씩 나누어 준다")
    void dealTest() {
        // when
        Cards cards = Cards.deal(() -> new Card(Suit.HEART, Denomination.TWO));

        // then
        assertThat(cards.getSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 합 계산은 카드 숫자를 기본으로 한다")
    void calculateCardsTotalTest() {
        // given
        List<Card> arbitraryCards = List.of(
                new Card(Suit.HEART, Denomination.THREE),
                new Card(Suit.HEART, Denomination.THREE),
                new Card(Suit.HEART, Denomination.QUEEN),
                new Card(Suit.HEART, Denomination.KING)
        );
        Cards cards = new Cards(arbitraryCards);

        // when
        Score actualTotal = cards.calculateCardsTotalScore();

        // then
        Score expectedTotal = Score.from(26);
        assertThat(actualTotal.equals(expectedTotal)).isTrue();
    }

    @Test
    @DisplayName("Ace는 1 또는 11로 계산할 수 있다")
    void calculateCardsTotalWithAceTest() {
        // given
        List<Card> arbitraryCards = List.of(
                new Card(Suit.HEART, Denomination.THREE),
                new Card(Suit.HEART, Denomination.THREE),
                new Card(Suit.HEART, Denomination.FOUR),
                new Card(Suit.HEART, Denomination.ACE)
        );
        Cards cards = new Cards(arbitraryCards);

        // when
        Score actualTotal = cards.calculateCardsTotalScore();

        // then
        Score expectedTotal = Score.from(21);
        assertThat(actualTotal.equals(expectedTotal)).isTrue();
    }

    @Test
    @DisplayName("BlackJack인지 확인한다")
    void checkBlackJackTest() {
        // given
        List<Card> arbitraryCards = List.of(
                new Card(Suit.HEART, Denomination.JACK),
                new Card(Suit.HEART, Denomination.ACE)
        );
        Cards cards = new Cards(arbitraryCards);

        // when & then
        assertThat(cards.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("카드를 뽑는다")
    void addCardTest() {
        // when
        List<Card> arbitraryCards = List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        );
        Cards cards = new Cards(arbitraryCards);

        // when
        cards.addCard(() -> new Card(Suit.HEART, Denomination.TWO));

        // then
        assertThat(cards.getSize()).isEqualTo(3);
    }
}
