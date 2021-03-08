package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Symbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void isDrawable_true() {
        Dealer dealer1 = new Dealer();
        dealer1.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.JACK));
        dealer1.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.FIVE));
        Assertions.assertThat(dealer1.isDrawable()).isTrue();

        Dealer dealer2 = new Dealer();
        dealer2.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.JACK));
        dealer2.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.SIX));
        Assertions.assertThat(dealer2.isDrawable()).isFalse();
    }
}
