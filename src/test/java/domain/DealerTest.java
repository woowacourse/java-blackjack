package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    void 카드_덱에서_카드를_뽑는다() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck);

        Card card = dealer.pickCard();

        assertThat(card).isEqualTo(new Card(Denomination.KING, Emblem.DIAMOND));
    }

    @Test
    void 카드의_합을_계산한다() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck);

        dealer.add(new Card(Denomination.KING, Emblem.DIAMOND));
        dealer.add(new Card(Denomination.SIX, Emblem.SPADE));

        int result = dealer.calculateScore();

        assertThat(result).isEqualTo(16);
    }

    @Test
    void 점수의_합이_17보다_작으면_카드를_더_받아야_한다() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck);

        dealer.add(new Card(Denomination.TEN, Emblem.DIAMOND));
        dealer.add(new Card(Denomination.SIX, Emblem.SPADE));

        boolean result = dealer.isNecessaryMoreCard();
        assertThat(result).isTrue();
    }

    @Test
    void 점수의_합이_17이상이면_카드를_더_받을_수_없다() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck);

        dealer.add(new Card(Denomination.TEN, Emblem.DIAMOND));
        dealer.add(new Card(Denomination.SEVEN, Emblem.SPADE));

        boolean result = dealer.isNecessaryMoreCard();
        assertThat(result).isFalse();
    }
}
