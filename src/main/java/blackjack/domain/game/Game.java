package blackjack.domain.game;

import blackjack.domain.deck.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

public class Game {
    private static final int INITIAL_DEAL_REPEAT = 2;
    private static final int ONE_REPEAT = 1;

    private final Deck deck;
    private final Dealer dealer;

    public Game(Deck deck, Dealer dealer) {
        this.deck = deck;
        this.dealer = dealer;
    }


    public void initializeDealToParticipants(Players players) {
        deck.shuffle();
        giveCardToParticipant(dealer, INITIAL_DEAL_REPEAT);

        for (Player player : players.getPlayers()) {
            giveCardToParticipant(player, INITIAL_DEAL_REPEAT);
        }
    }

    public void hitTo(Player player) {
        giveCardToParticipant(player, ONE_REPEAT);
    }

    public void hitDealer() {
        giveCardToParticipant(dealer, ONE_REPEAT);
    }

    public boolean isDealerTurn() {
        return dealer.canReceive();
    }

    public boolean isDealerBust() {
        return dealer.isBust();
    }

    private void giveCardToParticipant(Participant participant, int repeat) {
        for (int i = 0; i < repeat; i++) {
            participant.receiveCard(deck.hit());
        }
    }

    public Dealer getDealer() {
        return dealer;
    }
}
