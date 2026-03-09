package service;

import domain.card.CardDeck;
import domain.player.Player;
import domain.player.PlayerGroups;
import domain.player.WinStatus;
import domain.vo.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameService {
    private PlayerGroups playerGroups;
    private CardDeck cardDeck = new CardDeck();

    public void joinPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();

        players.add(new Player(new Name("딜러")));

        for (String playerName : playerNames) {
            Player player = new Player(new Name(playerName));
            players.add(player);
        }

        playerGroups = new PlayerGroups(players);
    }

    public void initAllPlayerCard() {
        for (int i = 0; i < 2; i++) {
            giveAllPlayerCard();
        }
    }

    private void giveAllPlayerCard() {
        while (playerGroups.hasNextPlayer()) {
            playerGroups.drawCard(cardDeck.getCard());
            playerGroups.nextPlayer();
        }
    }

    public Map<String, List<String>> getPlayersStatus() {
        return playerGroups.playersStatus();
    }

    public boolean isRemainPlayer() {
        return playerGroups.hasNextPlayer();
    }

    public String getCurrentPlayerName() {
        return playerGroups.getCurrentPlayer()
                .getName();
    }

    public List<String> getCurrentPlayerCards(){
        return playerGroups.getCurrentPlayerCards();
    }

    public void selectHit(){
        playerGroups.drawCard(cardDeck.getCard());
    }

    public boolean isCurrentPlayerBust() {
        if (playerGroups.getCurrentPlayerCardSum() > 21){
            return true;
        }

        return false;
    }

    public void nextPlayer() {
        playerGroups.nextPlayer();
    }

    public boolean isDealerHit(){
        if (playerGroups.getDealer().getCardSum() < 17) {
            playerGroups.getDealer().addCard(cardDeck.getCard());
            return true;
        }
        return false;
    }

    public Map<String, Integer> getPlayersTotalScore() {
        return playerGroups.playersTotalScore();
    }

    public Map<String, WinStatus> result() {
        return playerGroups.getGameResult();
    }

    public int getPlayerGroupSize() {
        return playerGroups.getPlayerGroupSize();
    }

}
