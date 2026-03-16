package service;

import domain.card.CardDeck;
import domain.card.ShuffleStrategy;
import domain.player.Player;
import domain.player.PlayerGroups;
import domain.player.WinStatus;
import dto.ParticipantBetResult;
import dto.ParticipantResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameService {
    private static final int CARDS_PER_PLAYER_AT_START = 2;

    private PlayerGroups playerGroups;
    private CardDeck cardDeck;

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

    public List<ParticipantResult> getPlayersStatus() {
        return playerGroups.playersStatus();
    }

    public void hit(Player player) {
        player.addCard(cardDeck.draw());
    }

    public boolean dealerHit() {
        if (playerGroups.getDealer().isHit()) {
            playerGroups.getDealer().addCard(cardDeck.draw());
            return true;
        }
        return false;
    }

    public Map<String, WinStatus> result() {
        return playerGroups.getGameResult();
    }

    public List<ParticipantBetResult> bettingResult() {
        List<ParticipantBetResult> profitResults = new ArrayList<>();

        Map<String, Integer> bettingResult = playerGroups.getBettingResult();
        for (Map.Entry<String, Integer> playerBettingResult : bettingResult.entrySet()) {
            Integer playerCost = playerBettingResult.getValue();
            profitResults.add(new ParticipantBetResult(playerBettingResult.getKey(), playerCost));
            playerGroups.addDealerCost(Math.negateExact(playerCost));
        }

        profitResults.addFirst(new ParticipantBetResult(playerGroups.getDealerName(), playerGroups.getDealerCost()));
        return profitResults;
    }

    public int getPlayerGroupSize() {
        return playerGroups.getPlayerGroupSize();
    }

    public ParticipantResult getDealerResult() {
        return playerGroups.getDealerResult();
    }

    public void createDeck(ShuffleStrategy shuffleStrategy) {
        cardDeck = new CardDeck(shuffleStrategy);
    }
}
