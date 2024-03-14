package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.strategy.CardShuffleStrategy;

public class Dealer extends Participant {
    private static final String DEFAULT_NAME_OF_DEALER = "딜러";
    private static final int DEALER_MIN_SCORE_POLICY = 16;

    private final CardDeck cardDeck;
    private final CardShuffleStrategy cardShuffleStrategy;

    public Dealer(final CardDeck cardDeck, final CardShuffleStrategy cardShuffleStrategy) {
        super(DEFAULT_NAME_OF_DEALER);
        this.cardDeck = cardDeck;
        this.cardShuffleStrategy = cardShuffleStrategy;
    }

    public void shuffleCards() {
        cardDeck.shuffle(cardShuffleStrategy);
    }

    public Card pickCard() {
        return cardDeck.draw();
    }

    @Override
    public boolean canReceiveCard() {
        return calculateScore() <= DEALER_MIN_SCORE_POLICY;
    }
}
