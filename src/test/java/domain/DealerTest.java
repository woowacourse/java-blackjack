package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    void 카드_덱에서_카드를_뽑는다() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer("딜러", cardDeck);

        Card card = dealer.pickCard();

        assertThat(card).isEqualTo(new Card(Denomination.KING, Emblem.DIAMOND));
    }

    @Test
    void 카드의_합을_계산한다() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer("딜러", cardDeck);

        dealer.add(new Card(Denomination.KING, Emblem.DIAMOND));
        dealer.add(new Card(Denomination.SIX, Emblem.SPADE));

        int result = dealer.calculateScore();

        assertThat(result).isEqualTo(16);
    }
}
