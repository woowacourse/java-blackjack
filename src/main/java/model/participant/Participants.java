package model.participant;

import constant.ErrorMessage;
import model.result.ParticipantCurrentHand;
import java.util.List;
import model.card.Card;

public class Participants {
    private final Dealer dealer = new Dealer();
    private final Players players;

    public Participants() {
        this.players = new Players();
    }

    public void addPlayer(String playerName) {
        if(dealer.isSameName(playerName)) {
            throw new IllegalArgumentException(ErrorMessage.NO_PLAYER_NAME_DEALER.getMessage());
        }
        Player player = new Player(playerName);
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

    public Card getDealerFirstCard() {
        return dealer.getInitialCard();
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

    public DealerStatus getDealerStatus() {
        return dealer.getDealerStatus();
    }

    public List<PlayerStatus> getPlayerStatuses() {
        return players.getPlayerStatuses();
    }
}
