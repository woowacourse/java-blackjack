package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.component.CardNumber;
import blackjack.domain.card.component.CardFigure;
import blackjack.domain.user.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    @DisplayName("딜러의 카드 합이 16미만일 때 확인")
    @Test
    void canReceiveMoreCardTest_SUM_UNDER_16() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.FIVE, CardFigure.CLOVER));
        dealer.addCard(new Card(CardNumber.FIVE, CardFigure.HEART));

        boolean expected = true;
        assertThat(dealer.receivable()).isEqualTo(expected);
    }

    @DisplayName("딜러의 카드 합이 16초과할 때 확인")
    @Test
    void canReceiveMoreCard_SUM_OVER_16() {
        Dealer dealer = new Dealer();

        dealer.addCard(new Card(CardNumber.QUEEN, CardFigure.CLOVER));
        dealer.addCard(new Card(CardNumber.KING, CardFigure.CLOVER));

        boolean expected = false;
        assertThat(dealer.receivable()).isEqualTo(expected);
    }
}
