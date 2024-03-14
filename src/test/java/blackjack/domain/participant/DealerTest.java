package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.strategy.ReverseCardShuffleStrategy;
import org.junit.jupiter.api.Test;

import static blackjack.fixture.DealerFixture.딜러;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    void 카드를_한_장_뽑을_수_있다() {
        final Dealer dealer = 딜러();
        final Card card = dealer.pickCard();

        assertThat(card).isEqualTo(new Card(Suit.DIAMOND, Denomination.KING));
    }

    @Test
    void 딜러는_카드를_셔플할_수_있다() {
        final Dealer dealer = 딜러();

        dealer.shuffleCards(new ReverseCardShuffleStrategy());
        final Card card = dealer.pickCard();

        assertThat(card).isEqualTo(new Card(Suit.HEART, Denomination.ACE));
    }

    @Test
    void 딜러_카드의_총_점수를_계산할_수_있다() {
        final Dealer dealer = 딜러();
        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));
        dealer.receiveCard(new Card(Suit.SPADE, Denomination.SIX));

        final int result = dealer.calculateScore();

        assertThat(result).isEqualTo(16);
    }

    @Test
    void 딜러가_카드의_총_점수가_16_이하라면_카드를_더_받을_수_있다() {
        final Dealer dealer = 딜러();
        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));
        dealer.receiveCard(new Card(Suit.SPADE, Denomination.SIX));

        final boolean result = dealer.canReceiveCard();

        assertThat(result).isTrue();
    }

    @Test
    void 딜러가_카드의_총_점수가_17_이상이라면_카드를_더_받을_수_없다() {
        final Dealer dealer = 딜러();
        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));
        dealer.receiveCard(new Card(Suit.SPADE, Denomination.SEVEN));

        final boolean result = dealer.canReceiveCard();

        assertThat(result).isFalse();
    }
}
