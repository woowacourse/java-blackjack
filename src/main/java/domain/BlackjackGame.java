package domain;

import domain.card.Deck;
import domain.card.ShuffleStrategy;
import domain.participant.Dealer;
import domain.participant.Decision;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final Players players;
    private final Dealer dealer;
    private Deck deck;

    public BlackjackGame(Players players) {
        this.players = players;
        this.dealer = new Dealer();
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

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>(List.of(dealer));
        participants.addAll(players.getPlayers());
        return participants;
    }

    public Map<String, Integer> getRevenues() {
        Map<String, Integer> playersRevenues = players.calculateRevenues(dealer);
        int dealerRevenues = calculateDealerRevenue(playersRevenues);
        Map<String, Integer> revenues = new LinkedHashMap<>();
        revenues.put(dealer.name(), dealerRevenues);
        revenues.putAll(playersRevenues);
        return revenues;
    }

    private int calculateDealerRevenue(Map<String, Integer> playersRevenues) {
        return playersRevenues.values().stream()
                .mapToInt(Integer::intValue)
                .sum() * -1;
    }
}
