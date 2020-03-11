package domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.deck.Card;
import domain.deck.Symbol;
import domain.deck.Type;

class DealerTest {

    @Test
    void create() {
        assertThat(Dealer.appoint()).isNotNull();
    }

    @Test
    void getFirstDrawResult() {
        Dealer dealer = Dealer.appoint();
        dealer.draw(new Card(Symbol.CLOVER, Type.EIGHT));
        dealer.draw(new Card(Symbol.DIAMOND, Type.ACE));

        assertThat(dealer.getFirstDrawResult()).isEqualTo("딜러: 8클로버");
    }
}