package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    @DisplayName("덱에서 카드의 합을 구한다")
    void countCardNumber() {
        Deck deck = new Deck();
        deck.addCard(new Card(Suit.CLUB, Number.THREE));
        deck.addCard(new Card(Suit.HEART, Number.FOUR));
        int bestNumber = deck.countBestNumber();

        assertThat(bestNumber).isEqualTo(7);
    }

    @Test
    @DisplayName("에이스가 포함 될 경우 21에 가까운 숫자를 반환한다")
    void countCardNumberContainsAce() {
        Deck deck = new Deck();
        deck.addCard(new Card(Suit.CLUB, Number.ACE));
        deck.addCard(new Card(Suit.HEART, Number.TEN));
        int bestNumber = deck.countBestNumber();

        assertThat(bestNumber).isEqualTo(21);
    }
}
