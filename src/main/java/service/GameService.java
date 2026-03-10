package service;

import domain.card.CardDeck;
import domain.player.Player;
import domain.player.PlayerGroups;
import domain.player.WinStatus;
import dto.DealerResult;
import dto.PlayerResult;

import java.util.List;
import java.util.Map;

public class GameService {
    private static final int CARDS_PER_PLAYER_AT_START = 2;

    private PlayerGroups playerGroups;
    private final CardDeck cardDeck = new CardDeck();

    public void joinPlayers(List<String> playerNames) {
        playerGroups = new PlayerGroups(playerNames);
    }

    public void initAllPlayerCard() {
        for (int i = 0; i < CARDS_PER_PLAYER_AT_START; i++) {
            giveAllPlayerCard();
        }
    }

    public List<Player> getPlayers() {
        return playerGroups.getPlayers();
    }

    private void giveAllPlayerCard() {
        for (Player player : playerGroups.getPlayers()) {
            player.addCard(cardDeck.draw());
        }

        playerGroups.drawDealerCard(cardDeck.draw());
    }

    // 플레이어들 정보 전달
    public List<PlayerResult> getPlayersStatus() {
        return playerGroups.playersStatus();
    }

    // 히트
    public void hit(Player player) {
        player.addCard(cardDeck.draw());
    }

    // 딜러 히트
    public boolean dealerHit() {
        if (playerGroups.getDealer().isHit()) {
            playerGroups.getDealer().addCard(cardDeck.draw());
            return true;
        }
        return false;
    }

    public Map<String, Integer> getPlayersTotalScore() {
        return playerGroups.getPlayerTotalScore();
    }

    public Map<String, WinStatus> result() {
        return playerGroups.getGameResult();
    }

    public int getPlayerGroupSize() {
        return playerGroups.getPlayerGroupSize();
    }

    public DealerResult getDealerResult() {
        return playerGroups.getDealerResult();
    }
}
