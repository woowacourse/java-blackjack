package service;

import domain.card.CardDeck;
import domain.player.Player;
import domain.player.PlayerGroups;
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

    // 플레이어들 정보 전달
    public Map<String, List<String>> getPlayersStatus() {
        return playerGroups.playersStatus();
    }

    public int getPlayerGroupSize() {
        return playerGroups.getPlayerGroupSize();
    }
}
