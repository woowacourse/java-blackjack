package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final Players players;
    private final Dealer dealer;
    private Deck deck;

    private BlackjackGame(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static BlackjackGame createWithPlayerNames(List<String> playerNames) {
        Players players = Players.from(playerNames);
        Dealer dealer = new Dealer();
        return new BlackjackGame(players, dealer);
    }

    public void handOutInitialCards(ShuffleStrategy shuffleStrategy) {
        deck = Deck.create();
        deck.shuffle(shuffleStrategy);

        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());
        players.receiveCard(deck);
        players.receiveCard(deck);
    }

    public boolean hasDrawablePlayer() {
        return players.hasDrawablePlayer();
    }

    public Player getCurrentDrawablePlayer() {
        return players.getCurrentDrawablePlayer();
    }

    public void hitOrStand(Decision decision) {
        if (decision == Decision.HIT) {
            players.handOutCardToCurrentPlayer(deck.draw());
            return;
        }
        players.standCurrentPlayer();
    }

    public boolean isDealerDrawable() {
        return dealer.isDrawable();
    }

    public void handOutCardToDealer() {
        dealer.receiveCard(deck.draw());
    }

    public Map<String, GameOutcome> getPlayersOutcome() {
        return players.battleWith(dealer);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>(List.of(dealer));
        participants.addAll(players.getPlayers());
        return participants;
    }
}
