package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

class DealerTest {

    @Test
    @DisplayName("딜러는 카드 번호합 17 이상이면 더 뽑을 수 없다.")
    void dealerDrawable() {
        Dealer dealer = new Dealer();
        Card card1 = Card.getInstance(CardShape.SPADE, CardNumber.TEN);
        Card card2 = Card.getInstance(CardShape.SPADE, CardNumber.SEVEN);

        dealer.addCard(card1);
        dealer.addCard(card2);
        assertThat(dealer.isDrawable()).isFalse();
    }
}