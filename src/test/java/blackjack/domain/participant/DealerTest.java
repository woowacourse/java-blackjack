package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.CardPoint;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러가_카드를_받는다() {

        Dealer dealer = new Dealer();

        dealer.recieveCard(new Card(CardPoint.ACE, CardPattern.DIAMOND));

        assertThat(dealer.getCardCount()).isEqualTo(1);

    }

}
