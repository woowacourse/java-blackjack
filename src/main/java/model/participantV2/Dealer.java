package model.participantV2;

import java.util.List;
import java.util.stream.IntStream;
import model.Deck.Card;
import model.Deck.Deck;

public final class Dealer extends ParticipantV2 {
    private static final int DEALER_HIT_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    @Override
    public void receiveCard(Card card) {
        participantHand.add(card);
    }

    @Override
    public void dealInitialCards(final Deck deck) {
        IntStream.range(0, INITIAL_DEAL_CARD_COUNT).forEach(
                i -> receiveCard(deck.pick())
        );
    }

    @Override
    public boolean isBurst() {
        return participantHand.checkBurst();
    }

    @Override
    public int calculateFinalScore() {
        return participantHand.calculateFinalScore();
    }

    @Override
    public List<Card> getHandCards() {
        return participantHand.getCards();
    }

    ///

    public Card getFirstHand() {
        return getParticipantHand().getCards().getFirst();
    }

    public boolean checkScoreUnderSixteen() {
        return getParticipantHand().checkScoreBelow(DEALER_HIT_THRESHOLD);
    }
}

