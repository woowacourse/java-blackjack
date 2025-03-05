package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @DisplayName("처음 카드를 받은 후 딜러는 하나의 카드만 오픈한다")
    @Test
    void getInitialCardTest() {
        // given
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardShape.CLOVER, CardValue.TEN);
        Card card2 = new Card(CardShape.HEART, CardValue.EIGHT);
        dealer.addCard(card1);
        dealer.addCard(card2);

        // when
        Card result = dealer.getInitialCard();

        // then
        assertThat(result).isEqualTo(card1);
    }
}
