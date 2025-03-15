package model.participant;

import java.util.List;
import java.util.stream.IntStream;
import model.deck.Card;
import model.deck.Deck;

public final class Dealer {
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int INITIAL_DEAL_CARD_COUNT = 2;

    private final ParticipantHand participantHand;

    public Dealer() {
        this.participantHand = new ParticipantHand();
    }

    public void receiveCard(final Card card) {
        participantHand.add(card);
    }

    public boolean isBurst() {
        return participantHand.checkBurst();
    }

    public void dealInitialCards(final Deck deck) {
        //TODO : INITIAL_DEAL_COUNT 가 여기 있어야 할까?
        IntStream.range(0, INITIAL_DEAL_CARD_COUNT).forEach(
                i -> receiveCard(deck.pick())
        );
    }

    public int calculateFinalScore() {
        return participantHand.calculateFinalScore();
    }

    public boolean canHit() {
        return participantHand.checkScoreBelow(DEALER_HIT_THRESHOLD);
    }

    public List<Card> openInitialDeal() {
        return List.of(participantHand.getCards().getFirst());
    }

    public List<Card> getHandCards() {
        return participantHand.getCards();
    }
}

