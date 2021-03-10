package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    public void create() {
        Card card = new Card(Denomination.JACK, Suit.CLOVER);
        assertThat(card).isEqualTo(new Card(Denomination.JACK, Suit.CLOVER));
    }

    @Test
    public void getName() {
        Card card = new Card(Denomination.THREE, Suit.DIAMOND);
        assertThat(card.getName()).isEqualTo("3다이아몬드");
    }

    @Test
    public void getScore() {
        Card card = new Card(Denomination.THREE, Suit.SPADE);
        assertThat(card.findScore()).isEqualTo(3);
    }

}
