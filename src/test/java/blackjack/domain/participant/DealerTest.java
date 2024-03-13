package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.strategy.CardShuffleStrategy;
import blackjack.domain.strategy.ReverseCardShuffleStrategy;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    void 카드를_한_장_뽑을_수_있다() {
        final CardDeck cardDeck = new CardDeck();
        final CardShuffleStrategy cardShuffleStrategy = new ReverseCardShuffleStrategy();
        final Dealer dealer = new Dealer(cardDeck, cardShuffleStrategy);

        final Card card = dealer.pickCard();

        assertThat(card).isEqualTo(new Card(Suit.DIAMOND, Denomination.KING));
    }

    @Test
    void 딜러는_카드를_셔플할_수_있다() {
        final CardDeck cardDeck = new CardDeck();
        final CardShuffleStrategy cardShuffleStrategy = new ReverseCardShuffleStrategy();
        final Dealer dealer = new Dealer(cardDeck, cardShuffleStrategy);

        dealer.shuffleCards();
        final Card card = dealer.pickCard();

        assertThat(card).isEqualTo(new Card(Suit.HEART, Denomination.ACE));
    }

    @Test
    void 딜러는_카드를_받을_수_있다() {
        final CardDeck cardDeck = new CardDeck();
        final CardShuffleStrategy cardShuffleStrategy = new ReverseCardShuffleStrategy();
        final Dealer dealer = new Dealer(cardDeck, cardShuffleStrategy);

        dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));

        assertThat(dealer).extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(1);
    }
}
