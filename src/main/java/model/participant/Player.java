package model.participant;

import java.util.List;
import java.util.stream.IntStream;
import model.deck.Card;
import model.deck.Deck;

public final class Player {
    private static final int INITIAL_DEAL_CARD_COUNT = 2;

    private final String name;
    private final ParticipantHand participantHand;

    public Player(final String name) {
        this.name = name;
        this.participantHand = new ParticipantHand();
    }

    public void receiveCard(final Card card) {
        participantHand.add(card);
    }

    public boolean isBurst() {
        return participantHand.checkBurst();
    }

    public void dealInitialCards(final Deck deck) {
        IntStream.range(0, INITIAL_DEAL_CARD_COUNT).forEach(
                i -> receiveCard(deck.pick())
        );
    }

    public int calculateFinalScore() {
        return participantHand.calculateFinalScore();
    }

    public List<Card> openInitialDeal() {
        return participantHand.getCards();
    }

    public boolean canHit() {
        return !isBurst();
    }

    public List<Card> getHandCards() {
        return participantHand.getCards();
    }


    public String getName() {
        return name;
    }

    public boolean isBlackjack() {
        return participantHand.checkBlackJack();
    }
}
