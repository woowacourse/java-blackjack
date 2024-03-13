package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.strategy.CardShuffleStrategy;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private final CardDeck cardDeck;
    private final CardShuffleStrategy cardShuffleStrategy;
    private final List<Card> cards;

    public Dealer(final CardDeck cardDeck, final CardShuffleStrategy cardShuffleStrategy) {
        this.cardDeck = cardDeck;
        this.cardShuffleStrategy = cardShuffleStrategy;
        this.cards = new ArrayList<>();
    }

    public void shuffleCards() {
        cardDeck.shuffle(cardShuffleStrategy);
    }

    public Card pickCard() {
        return cardDeck.draw();
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }
}
