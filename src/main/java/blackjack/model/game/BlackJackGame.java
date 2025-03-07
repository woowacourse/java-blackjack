package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import blackjack.model.player.Player;

public class BlackJackGame {
    private final Deck deck;
    private final Participants participants;
    private final Dealer dealer;
    private final Turn turn;

    public BlackJackGame(DeckInitializer deckInitializer, Dealer dealer, Participants participants) {
        this.deck = deckInitializer.generateDeck();
        this.participants = participants;
        this.dealer = dealer;
        this.turn = new Turn(participants);
    }

    public void initializeGame() {
        participants.stream().forEach(this::putTowCard);
        putTowCard(dealer);
    }

    public void receiveCard(boolean isReceive) {
        if (!isReceive) {
            turn.changeTurn();
            return;
        }
        turn.drawCardCurrentTurnParticipant(deck.drawCard());
    }

    public Participant getCurrentTurnParticipant() {
        return turn.getCurrentTurnParticipant();
    }

    public boolean isDrawableDealerCard() {
        return dealer.isDrawable();
    }

    public void drawDealerCards() {
        dealer.putCard(deck.drawCard());
    }

    public boolean hasReadyParticipant() {
        return turn.hasReadyParticipant();
    }

    public void changeNextTurn() {
        turn.skipTurn();
    }

    private void putTowCard(Player player) {
        player.putCard(deck.drawCard());
        player.putCard(deck.drawCard());
    }
}
