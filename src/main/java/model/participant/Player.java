package model.participant;

import java.util.List;
import java.util.stream.IntStream;
import model.betting.Bet;
import model.betting.Bettable;
import model.deck.Card;
import model.deck.Deck;
import model.deck.Gameable;

public final class Player implements Bettable, Gameable {
    private static final int INITIAL_DEAL_CARD_COUNT = 2;

    private final String name;
    private ParticipantHand participantHand; //TODO : not final
    //TODO : player가 bet을 가져야할까?

    public Player(final String name) {
        this.name = name;
        this.participantHand = new HardHand();
    }

    @Override
    public void receiveCard(final Card card) {
        if (card.isSoftCard()) {
            this.participantHand = this.participantHand.cloneToSoftHand();
        }
        participantHand.add(card);
    }

    @Override
    public boolean isBurst() {
        return participantHand.checkBurst();
    }

    @Override
    public boolean isBlackjack() {
        return participantHand.checkBlackJack();
    }

    @Override
    public int calculateFinalScore() {
        return participantHand.calculateFinalScore();
    }

    @Override
    public List<Card> openInitialDeal() {
        return participantHand.getCards();
    }

    @Override
    public boolean canHit() {
        return !isBurst();
    }

    @Override
    public List<Card> getHandCards() {
        return participantHand.getCards();
    }

    @Override
    public Bet makeBet(int betAmount) {
        return new Bet(betAmount, this);
    }

    @Override
    public int calculateRevenue() {
        return 0; //TODO 수정해야함
    }

    public void dealInitialCards(final Deck deck) {
        IntStream.range(0, INITIAL_DEAL_CARD_COUNT).forEach(
                i -> receiveCard(deck.pick())
        );
    }

    public String getName() {
        return name;
    }
}
