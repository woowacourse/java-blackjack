package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.strategy.CardShuffleStrategy;

public class Dealer {
    private final CardDeck cardDeck;
    private final CardShuffleStrategy cardShuffleStrategy;

    public Dealer(final CardDeck cardDeck, final CardShuffleStrategy cardShuffleStrategy) {
        this.cardDeck = cardDeck;
        this.cardShuffleStrategy = cardShuffleStrategy;
    }

    public void shuffleCards() {
        cardDeck.shuffle(cardShuffleStrategy);
    }

    public Card pickCard() {
        return cardDeck.draw();
    }
}
