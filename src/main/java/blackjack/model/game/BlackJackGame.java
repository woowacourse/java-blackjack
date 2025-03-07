package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import blackjack.model.player.Player;

import java.util.ArrayDeque;
import java.util.Queue;

public class BlackJackGame {
    private final Deck deck;
    private final Participants participants;
    private final Dealer dealer;
    private final Queue<Participant> readyQueue;

    public BlackJackGame(final DeckInitializer deckInitializer, final Dealer dealer, final Participants participants) {
        this.deck = deckInitializer.generateDeck();
        this.participants = participants;
        this.dealer = dealer;
        this.readyQueue = new ArrayDeque<>(participants.getParticipants());
    }

    public void initializeGame() {
        participants.getParticipants()
                .stream()
                .forEach(this::giveTwoCards);
        giveTwoCards(dealer);
    }

    private void giveTwoCards(final Player player) {
        player.putCard(deck.drawCard());
        player.putCard(deck.drawCard());
    }

    public void giveCardToCurrentTurnParticipant(final boolean isPlayerWantCard) {
        if (isPlayerWantCard) {
            readyQueue.peek().putCard(deck.drawCard());
            return;
        }
        readyQueue.poll();
    }

    public Participant getCurrentTurnParticipant() {
        return readyQueue.peek();
    }

    public boolean isDealerCardDrawable() {
        return dealer.isCardDrawable();
    }

    public void drawDealerCard() {
        dealer.putCard(deck.drawCard());
    }

    public boolean canGiveCardToParticipant() {
        return !readyQueue.isEmpty();
    }

    public void skipTurn() {
        readyQueue.poll();
    }
}
