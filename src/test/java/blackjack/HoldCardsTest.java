package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HoldCardsTest {

    @Test
    @DisplayName("덱에서 카드의 합을 구한다")
    void countCardNumber() {
        HoldCards holdCards = new HoldCards();
        holdCards.addCard(Card.valueOf(Suit.CLUB, Number.THREE));
        holdCards.addCard(Card.valueOf(Suit.HEART, Number.FOUR));
        int bestNumber = holdCards.countBestNumber();

        assertThat(bestNumber).isEqualTo(7);
    }

    @Test
    @DisplayName("에이스가 포함 될 경우 21에 가까운 숫자를 반환한다")
    void countCardNumberContainsAce() {
        HoldCards holdCards = new HoldCards();
        holdCards.addCard(Card.valueOf(Suit.CLUB, Number.ACE));
        holdCards.addCard(Card.valueOf(Suit.HEART, Number.TEN));
        int bestNumber = holdCards.countBestNumber();

        assertThat(bestNumber).isEqualTo(21);
    }
}
