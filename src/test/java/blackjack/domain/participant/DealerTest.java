package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.strategy.CardShuffleStrategy;
import blackjack.domain.strategy.ReverseCardShuffleStrategy;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        final CardDeck cardDeck = new CardDeck();
        final CardShuffleStrategy cardShuffleStrategy = new ReverseCardShuffleStrategy();

        dealer = new Dealer(cardDeck, cardShuffleStrategy);
    }

    @Test
    void 카드를_한_장_뽑을_수_있다() {
        final Card card = dealer.pickCard();

        assertThat(card).isEqualTo(new Card(Suit.DIAMOND, Denomination.KING));
    }

    @Test
    void 딜러는_카드를_셔플할_수_있다() {
        dealer.shuffleCards();
        final Card card = dealer.pickCard();

        assertThat(card).isEqualTo(new Card(Suit.HEART, Denomination.ACE));
    }

    @Test
    void 딜러_카드의_총_점수를_계산할_수_있다() {
        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));
        dealer.receiveCard(new Card(Suit.SPADE, Denomination.SIX));

        final int result = dealer.calculateScore();

        assertThat(result).isEqualTo(16);
    }

    @Test
    void 딜러가_카드의_총_점수가_16_이하라면_카드를_더_받을_수_있다() {
        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));
        dealer.receiveCard(new Card(Suit.SPADE, Denomination.SIX));

        final boolean result = dealer.canReceiveCard();

        assertThat(result).isTrue();
    }

    @Test
    void 딜러가_카드의_총_점수가_17_이상이라면_카드를_더_받을_수_없다() {
        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));
        dealer.receiveCard(new Card(Suit.SPADE, Denomination.SEVEN));

        final boolean result = dealer.canReceiveCard();

        assertThat(result).isFalse();
    }
}
