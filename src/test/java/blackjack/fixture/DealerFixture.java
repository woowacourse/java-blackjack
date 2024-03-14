package blackjack.fixture;

import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.strategy.CardShuffleStrategy;
import blackjack.domain.strategy.ReverseCardShuffleStrategy;

public class DealerFixture {

    public static Dealer 딜러() {
        final CardDeck cardDeck = new CardDeck();
        final CardShuffleStrategy cardShuffleStrategy = new ReverseCardShuffleStrategy();

        return new Dealer(cardDeck, cardShuffleStrategy);
    }
}
