package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DealerTest {
    @Test
    @DisplayName("딜러는 고유 숫자값 총합이 16이하 여부를 반환")
    void test1() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.JACK, Suit.SPADE));
        dealer.addCard(new Card(Denomination.TEN, Suit.SPADE));

        assertThat(dealer.isBelowThreshold()).isFalse();
    }

    @Test
    void shuffleDeck() {
    }

    @Test
    void drawCard() {
    }

    @Test
    void openOneCard() {
    }

    @Test
    void getExtraHandSize() {
    }
}
