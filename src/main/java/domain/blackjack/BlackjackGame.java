package domain.blackjack;

import domain.card.Card;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Player;
import domain.player.Players;

public class BlackjackGame {
    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Players players) {
        this.deck = new Deck();
        this.dealer = new Dealer();

        this.players = players;
    }

    public void giveInitialCards() {
        giveInitialCardsToDealer();
        giveInitialCardsToPlayers();
    }

    private void giveInitialCardsToDealer() {
        giveCardTo(dealer);
        giveCardTo(dealer);
    }

    private void giveInitialCardsToPlayers() {
        for (Player player : players.getPlayers()) {
            giveCardTo(player);
            giveCardTo(player);
        }
    }

    public void giveCardTo(Participant participant) {
        Card card = deck.drawCard();
        participant.receive(card);
    }

    public void giveAdditionalCardToDealer() {
        while (dealer.calculateScore() < BlackjackRule.DEALER_STANDARD_OF_HIT.getValue()) {
            giveCardTo(dealer);
        }
    }

    public void decideResult() {
        dealer.decideResults(players);
    }

    public int getDealerHitCardCount() {
        return dealer.getHitCardCount();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
