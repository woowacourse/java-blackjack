package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import java.util.Deque;
import java.util.LinkedList;

public class BlackJackGame2 {
    public static final int ACE_THRESHOLD = 16;

    private final Deck deck;
    private final Players players;
    private final Dealer dealer;
    private final Deque<Player> readyQueue;

    public BlackJackGame2(DeckInitializer deckInitializer, Dealer dealer, Players players) {
        this.deck = deckInitializer.generateDeck();
        this.players = players;
        this.dealer = dealer;
        this.readyQueue = new LinkedList<>(players.getParticipants());
    }

    public void initializeGame() {
        players.getParticipants()
                .forEach(this::putTowCard);
        putTowCard(dealer);
    }

    private void putTowCard(Participant participant) {
        participant.putCard(deck.drawCard());
        participant.putCard(deck.drawCard());
    }

    public void receiveCard(boolean isReceive) {
        if (isReceive) {
            readyQueue.getFirst().putCard(deck.drawCard());
            return;
        }
        readyQueue.poll();
    }

    public void setBettingMoney(int bettingMoney) {

    }

    public Player getCurrentTurnParticipant() {
        return readyQueue.getFirst();
    }

    public boolean isDrawableDealerCard() {
        return dealer.calculatePoint() <= ACE_THRESHOLD;
    }

    public void drewDealerCards() {
        dealer.putCard(deck.drawCard());
    }

    public boolean hasReady() {
        return !readyQueue.isEmpty();
    }

    public void skipTurn() {
        readyQueue.poll();
    }
}
