package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardHand;
import blackjack.domain.strategy.CardShuffleStrategy;

public class Dealer {
    private final CardDeck cardDeck;
    private final CardShuffleStrategy cardShuffleStrategy;
    private final CardHand cardHand;

    public Dealer(final CardDeck cardDeck, final CardShuffleStrategy cardShuffleStrategy) {
        this.cardDeck = cardDeck;
        this.cardShuffleStrategy = cardShuffleStrategy;
        this.cardHand = new CardHand();
    }

    public void shuffleCards() {
        cardDeck.shuffle(cardShuffleStrategy);
    }

    public Card pickCard() {
        return cardDeck.draw();
    }

    public void receiveCard(final Card card) {
        cardHand.receiveCard(card);
    }

    public int calculateScore() {
        return cardHand.sumAllScore();
    }
}
