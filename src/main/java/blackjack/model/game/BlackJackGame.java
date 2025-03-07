package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import blackjack.model.player.Player;

import java.util.ArrayDeque;
import java.util.Deque;

public class BlackJackGame {
    private final Deck deck;
    private final Participants participants;
    private final Dealer dealer;
    private final Deque<Participant> readyQueue;

    public BlackJackGame(final DeckInitializer deckInitializer, final Dealer dealer, final Participants participants) {
        this.deck = deckInitializer.generateDeck();
        this.participants = participants;
        this.dealer = dealer;
        this.readyQueue = new ArrayDeque<>(participants.getParticipants());
    }

    public void initializeGame() {
        participants.stream()
                .forEach(this::putTowCard);
        putTowCard(dealer);
    }

    private void putTowCard(final Player player) {
        player.putCard(deck.drawCard());
        player.putCard(deck.drawCard());
    }

    public void receiveCard(final boolean isPlayerWantCard) {
        if (isPlayerWantCard) {
            readyQueue.getFirst().putCard(deck.drawCard());
            return;
        }
        readyQueue.poll();
    }

    public Participant getCurrentTurnParticipant() {
        return readyQueue.getFirst();
    }

    public boolean isDealerCardDrawable() {
        return dealer.calculatePoint() <= 16;
    }

    public void drawDealerCard() {
        dealer.putCard(deck.drawCard());
    }

    public boolean hasReady() {
        return !readyQueue.isEmpty();
    }

    public void skipTurn() {
        readyQueue.poll();
    }
}
