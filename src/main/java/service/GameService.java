package service;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.TrumpNumber;
import domain.card.TrumpSuit;
import domain.player.Player;
import domain.player.PlayerGroups;
import domain.player.WinStatus;
import dto.ParticipantBetResult;
import dto.ParticipantResult;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<ParticipantBetResult> bettingResult(Map<String, Integer> userBetInfo) {
        List<ParticipantBetResult> profitResults = new ArrayList<>();
        int dealerCost = 0;

        for (Map.Entry<String, WinStatus> playerWinStatus : playerGroups.getGameResult().entrySet()) {
            String userName = playerWinStatus.getKey();
            int userCost = (int) (userBetInfo.get(userName) * playerWinStatus.getValue().getEarningsRate());

            profitResults.add(new ParticipantBetResult(userName, userCost));
            dealerCost -= userCost;
        }

        profitResults.addFirst(new ParticipantBetResult(playerGroups.getDealer().getName(), dealerCost));
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
