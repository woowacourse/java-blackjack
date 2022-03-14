package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HoldingCardTest {

    @Test
    @DisplayName("Ace가 포함되지 않은 카드의 합을 올바르게 계산한다.")
    void cardSumTestWithoutAce() {
        HoldingCards holdingCards = new HoldingCards();
        holdingCards.addCard(new Card(CardNumber.TWO, CardType.CLOVER));
        holdingCards.addCard(new Card(CardNumber.EIGHT, CardType.HEART));
        holdingCards.addCard(new Card(CardNumber.SIX, CardType.DIAMOND));

        assertThat(holdingCards.cardSum()).isEqualTo(16);
    }

    @Test
    @DisplayName("Ace가 포함된 카드의 합을 올바르게 계산한다.")
    void cardSumTestWithAcet() {
        HoldingCards holdingCards = new HoldingCards();
        holdingCards.addCard(new Card(CardNumber.ACE, CardType.CLOVER));
        holdingCards.addCard(new Card(CardNumber.TEN, CardType.HEART));

        assertThat(holdingCards.cardSum()).isEqualTo(21);
    }

    @Test
    @DisplayName("숫자의 합이 21을 넘는 경우 Ace는 1로 계산될 수 있다.")
    void cardSumTestWithoutAceBust() {
        HoldingCards holdingCards = new HoldingCards();
        holdingCards.addCard(new Card(CardNumber.ACE, CardType.CLOVER));
        holdingCards.addCard(new Card(CardNumber.ACE, CardType.HEART));
        holdingCards.addCard(new Card(CardNumber.ACE, CardType.DIAMOND));

        assertThat(holdingCards.cardSum()).isEqualTo(13);
    }
}
