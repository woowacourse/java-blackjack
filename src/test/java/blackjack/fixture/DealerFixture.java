package blackjack.fixture;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.strategy.CardShuffleStrategy;
import blackjack.domain.card.strategy.ReverseCardShuffleStrategy;
import blackjack.domain.participant.Dealer;

public class DealerFixture {

    public static Dealer 딜러() {
        final CardDeck cardDeck = new CardDeck();
        final CardShuffleStrategy cardShuffleStrategy = new ReverseCardShuffleStrategy();

        return new Dealer(cardDeck, cardShuffleStrategy);
    }
}
