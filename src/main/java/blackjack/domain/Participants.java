package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.WinningResult;
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
        for (Player player : players.getAllPlayers()) {
            player.distributeCards(deck);
        }
    }
    
    public String getPlayerNicknames() {
        return players.getAllPlayerNicknames();
    }
    
    public String getInitialResults() {
        String dealerInfo = dealer.getFirstCardInfoSnapshot();
        String playersInfo = players.getPlayersInfo();
        return String.format("%s\n%s", dealerInfo, playersInfo);
    }

    public String getTotalResults() {
        String dealerResult = dealer.getResultSnapshot();
        String playersResult = players.getPlayersResult();
        return String.format("%s\n%s", dealerResult, playersResult);
    }

    public WinningResult getWinningResult() {
        int dealerScore = dealer.getTotalScore();
        if (dealer.isBusted()) {
            dealerScore = 0;
        }
        return getWinningResult(dealerScore);
    }
    
    public WinningResult getWinningResult(int dealerScore) {
        return players.getWinningResult(dealerScore);
    }
    public String findDrawablePlayer() {
        return players.findDrawablePlayerNickname();
    }
    
    public String addCardToAvailablePlayer(Deck deck) {
        List<Card> drewCards = deck.drawCard();
        return players.addCardToAvailablePlayer(drewCards);
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
}
