package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HoldCardsTest {

    @Test
    @DisplayName("덱에서 카드의 합을 구한다")
    void countCardNumber() {
        HoldCards holdCards = new HoldCards(Card.valueOf(Suit.CLUB, Number.THREE), Card.valueOf(Suit.HEART, Number.FOUR));
        int bestNumber = holdCards.countBestNumber();

        assertThat(bestNumber).isEqualTo(7);
    }

    @Test
    @DisplayName("에이스가 포함 될 경우 21에 가까운 숫자를 반환한다")
    void countCardNumberContainsAce() {
        HoldCards holdCards = new HoldCards(Card.valueOf(Suit.CLUB, Number.ACE), Card.valueOf(Suit.HEART, Number.TEN));
        int bestNumber = holdCards.countBestNumber();

        assertThat(bestNumber).isEqualTo(21);
    }
}
