package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.ParticipantResult;
import blackjack.domain.participant.Player;
import blackjack.dto.DrawResult;
import blackjack.dto.PlayerResult;
import blackjack.dto.WinningResult;
import java.util.ArrayList;
import java.util.List;

public class Participants {
    
    private final Players players;
    private final Dealer dealer;
    
    public Participants(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }
    
    public PlayingCards distributeCards(PlayingCards deck) {
        List<Participant> participants = getParticipants();
        for (Participant participant : participants) {
            DrawResult drawResult = participant.distributeCards(deck);
            deck = drawResult.drewDeck();
        }
        return deck;
    }
    
    private List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getAllPlayers());
        return participants;
    }
    
    public List<ParticipantResult> getInitialResult() {
        List<ParticipantResult> participantResults = new ArrayList<>();
        ParticipantResult dealerParticipantResult = new ParticipantResult(dealer, dealer.getFirstCard());
        participantResults.add(dealerParticipantResult);
        for (Player player : players.getAllPlayers()) {
            participantResults.add(new ParticipantResult(player));
        }
        return participantResults;
    }
    
    public List<ParticipantResult> getGameResult() {
        List<Participant> participants = getParticipants();
        List<ParticipantResult> resultList = new ArrayList<>();
        for (Participant participant : participants) {
            resultList.add(new ParticipantResult(participant));
        }
        return resultList;
    }
    
    public WinningResult getWinningResult() {
        int dealerScore = dealer.getTotalScoreForResult();
        if (dealerScore == 0) {
            dealerScore = 1;
        }
        return getWinningResult(dealerScore);
    }
    
    public WinningResult getWinningResult(int dealerScore) {
        List<PlayerResult> playerResults = getPlayerWinningResult(dealerScore);
        int dealerLoss = (int) playerResults.stream()
                .filter(result -> result.gameResult() == GameResult.WIN)
                .count();
        int dealerWin = playerResults.size() - dealerLoss;
        return new WinningResult(dealerWin, dealerLoss, playerResults);
    }
    
    private List<PlayerResult> getPlayerWinningResult(int dealerScore) {
        return players.getWinningResults(dealerScore);
    }
    
    public String findDrawablePlayer() {
        return players.findDrawablePlayerNickname();
    }
    
    public DrawResult addCardToAvailablePlayer(PlayingCards deck) {
        DrawResult drawResult = deck.draw();
        PlayingCards drawCard = drawResult.drewCard();
        PlayingCards drawDeck = drawResult.drewDeck();
        
        PlayingCards playerHand = players.addCardToAvailablePlayer(drawCard);
        
        return new DrawResult(playerHand, drawDeck);
    }
    
    public void dontWandDraw() {
        players.dontWandDraw();
    }
    
    public boolean isDealerDraw() {
        return dealer.isDealerDraw();
    }
    
    public PlayingCards dealerDraw(PlayingCards deck) {
        DrawResult drawResult = deck.draw();
        PlayingCards drawCard = drawResult.drewCard();
        dealer.receiveCard(drawCard);
        return drawResult.drewDeck();
    }
    
    public List<String> getAllPlayerNickname() {
        return players.getAllPlayerNickname();
    }
    
    public ParticipantResult getDrawPlayerResult() {
        return new ParticipantResult(players.findDrawablePlayer());
    }
}
