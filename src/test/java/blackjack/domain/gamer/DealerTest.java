package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("보유 카드 번호 합이 특정 숫자를 넘었는지 확인")
    void checkCardsNumberSum() {
        Dealer dealer = new Dealer();
        Card card1 = Card.getInstance(CardShape.SPADE, CardNumber.TEN);
        Card card2 = Card.getInstance(CardShape.SPADE, CardNumber.FIVE);

        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.canDraw()).isEqualTo(true);
    }
}