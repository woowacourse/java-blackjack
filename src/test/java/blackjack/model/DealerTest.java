package blackjack.model;

import static blackjack.model.CardCreator.createCard;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 자신이_가진_카드의_합을_반환한다() {
        Dealer dealer = new Dealer();
        dealer.receiveCards(new Cards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));
        int expected = dealer.getCards().sumAll();

        assertThat(dealer.calculateSumOfCards()).isEqualTo(expected);
    }

    @Test
    void 카드를_받으면_자신의_카드에_추가한다() {
        Dealer dealer = new Dealer();

        dealer.receiveCards(new Cards(
                List.of(createCard(CardNumber.NINE), createCard(CardNumber.SIX), createCard(CardNumber.TWO))
        ));

        assertThat(dealer.getCards().getValues()).hasSize(3);
    }

}
