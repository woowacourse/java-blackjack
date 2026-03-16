package model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import model.card.Card;
import model.card.CardNumber;
import model.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    public void setUp() {
        dealer = new Dealer();
        dealer.addCard(new Card(Shape.DIAMOND, CardNumber.ACE));
        dealer.addCard(new Card(Shape.HEART, CardNumber.ACE));
    }

    @Test
    public void 첫_카드_가져오기_정상_작동() {
        Card card = new Card(Shape.DIAMOND, CardNumber.ACE);
        assertThat(dealer.getInitialCard()).isEqualTo(card.getString());
    }

    @Test
    public void 드로우_여부_정상_작동() {
        assertThat(dealer.canDraw()).isTrue();

        dealer.addCard(new Card(Shape.CLOVER, CardNumber.TWO));
        assertThat(dealer.canDraw()).isTrue();

        dealer.addCard(new Card(Shape.CLOVER, CardNumber.NINE));
        assertThat(dealer.canDraw()).isTrue();

        dealer.addCard(new Card(Shape.CLOVER, CardNumber.KING));
        assertThat(dealer.canDraw()).isFalse();
    }
}
