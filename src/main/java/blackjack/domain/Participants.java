package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.dto.ParticipantStatus;
import blackjack.dto.TotalWinningResult;
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
        dealer.distributeCards(deck);
        players.distributeCards(deck);
    }
    
    public List<ParticipantStatus> getParticipantsInitialStatus() {
        List<ParticipantStatus> participantStatuses = new ArrayList<>();
        participantStatuses.add(dealer.getInitialStatus());
        participantStatuses.addAll(getPlayersStatus());
        return participantStatuses;
    }
    
    public List<ParticipantStatus> getParticipantsStatus() {
        List<ParticipantStatus> participantStatuses = new ArrayList<>();
        participantStatuses.add(dealer.getCardStatus());
        participantStatuses.addAll(getPlayersStatus());
        return participantStatuses;
    }
    
    public List<ParticipantStatus> getPlayersStatus() {
        return players.getPlayerCardStatus();
    }
    
    public TotalWinningResult getWinningResult() {
        return players.getWinningResult(dealer);
    }
    
    public ParticipantStatus getDrawablePlayerInfo() {
        return players.getDrawablePlayerInfo();
    }
    
    public void dontWantDraw() {
        players.dontWantDraw();
    }
    
    public boolean isDealerDraw() {
        return dealer.isDrawable();
    }
    
    public void dealerDraw(Deck deck) {
        List<Card> drewCard = deck.drawCard();
        dealer.receiveCard(drewCard);
    }
    
    public ParticipantStatus giveCard(Deck deck) {
        List<Card> drewCards = deck.drawCard();
        return players.giveCard(drewCards);
    }
    
    public boolean isPlayerDraw() {
        return players.isDrawablePlayer();
    }
}
