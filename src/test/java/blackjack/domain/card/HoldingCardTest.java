package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HoldingCardTest {

    @Test
    @DisplayName("Ace가 포함되지 않은 카드의 합을 올바르게 계산한다.")
    void cardSumTestWithoutAce() {
        HoldingCard holdingCard = new HoldingCard();
        holdingCard.addCard(new Card(CardNumber.TWO, CardType.CLOVER));
        holdingCard.addCard(new Card(CardNumber.EIGHT, CardType.HEART));
        holdingCard.addCard(new Card(CardNumber.SIX, CardType.DIAMOND));

        assertThat(holdingCard.cardSum()).isEqualTo(16);
    }

    @Test
    @DisplayName("Ace가 포함된 카드의 합을 올바르게 계산한다.")
    void cardSumTestWithAcet() {
        HoldingCard holdingCard = new HoldingCard();
        holdingCard.addCard(new Card(CardNumber.ACE, CardType.CLOVER));
        holdingCard.addCard(new Card(CardNumber.TEN, CardType.HEART));

        assertThat(holdingCard.cardSum()).isEqualTo(21);
    }

    @Test
    @DisplayName("숫자의 합이 21을 넘는 경우 Ace는 1로 계산될 수 있다.")
    void cardSumTestWithoutAceBust() {
        HoldingCard holdingCard = new HoldingCard();
        holdingCard.addCard(new Card(CardNumber.ACE, CardType.CLOVER));
        holdingCard.addCard(new Card(CardNumber.ACE, CardType.HEART));
        holdingCard.addCard(new Card(CardNumber.ACE, CardType.DIAMOND));

        assertThat(holdingCard.cardSum()).isEqualTo(13);
    }
}
