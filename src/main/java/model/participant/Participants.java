package model.participant;

import dto.result.ParticipantCurrentHand;
import dto.result.ProfitResult;
import java.util.List;
import model.card.Card;

public class Participants {
    private final Dealer dealer = new Dealer();
    private final Players players = new Players();

    public void addPlayer(Player player) {
        dealer.validateSameName(player.getName());
        players.addPlayer(player);
    }

    public List<String> getPlayerNames() {
        return List.copyOf(players.getPlayerNames());
    }

    public void addBet(String playerName, BetPrice bet) {
        players.setBet(playerName, bet);
    }

    public void drawDealer(Card card) {
        dealer.addCard(card);
    }

    public void drawPlayer(String playerName, Card card) {
        players.drawCard(playerName, card);
    }

    public ParticipantCurrentHand getPlayerCurrentHand(String playerName) {
        return players.getPlayersCurrentHand(playerName);
    }

    public List<ParticipantCurrentHand> getPlayersHand() {
        return players.getPlayersHand();
    }

    public String getDealerFirstCard() {
        return dealer.getFirstCard();
    }

    public ParticipantCurrentHand getDealerHand() {
        return dealer.getCurrentHand();
    }

    public boolean isPlayerBust(String playerName) {
        return players.isPlayerBust(playerName);
    }

    public boolean isDealerCanDraw() {
        return dealer.canDraw();
    }

    public ProfitResult getProfitResult() {
        return players.getProfitResult(dealer.getDealerStatus());
    }
}
