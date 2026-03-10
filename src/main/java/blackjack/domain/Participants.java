package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class Participants {
    
    private final Players players;
    private final Dealer dealer;
    
    public Participants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }
    
    public void distributeCards(Deck deck) {
        dealer.drawInitialCards(deck);
        players.distributeCards(deck);
    }
    
    public Dealer getDealer() {
        return dealer;
    }
    
    public List<Player> getPlayers() {
        return players.getPlayers();
    }
    
    public List<Participant> getParticipants() {
        List<Participant> participantList = new ArrayList<>();
        participantList.add(dealer);
        participantList.addAll(getPlayers());
        return participantList;
    }
    
    public Player getCurrentPlayer() {
        return players.getDrawablePlayer();
    }
}
