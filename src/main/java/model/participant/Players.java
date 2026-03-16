package model.participant;

import constant.ErrorMessage;
import dto.result.ParticipantCurrentHand;
import dto.result.ParticipantProfit;
import dto.result.ProfitResult;
import dto.status.DealerStatus;
import dto.status.PlayerName;
import dto.status.PlayerStatus;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.card.Card;
import model.result.DealerProfit;
import model.result.PlayerProfits;
import model.result.ProfitCalculator;

public class Players {
    private final Map<String, Player> players = new LinkedHashMap<>();
    private final Map<String, Integer> playerBet = new HashMap<>();

    public void addPlayer(PlayerName playerName) {
        if(players.containsKey(playerName.name())) {
            throw new IllegalArgumentException((ErrorMessage.DUPLICATED_NAME.getMessage()));
        }

        Player player = new Player(playerName);
        players.put(player.getName(), player);
        playerBet.put(player.getName(), 0);
    }

    public List<String> getPlayerNames() {
        return List.copyOf(players.keySet());
    }

    public void setBet(String playerName, BetPrice bet) {
        playerBet.put(playerName, bet.value());
    }

    public void drawCard(String playerName, Card card) {
        Player player = getPlayer(playerName);
        player.addCard(card);
    }

    public ParticipantCurrentHand getPlayersCurrentHand(String playerName) {
        Player player = getPlayer(playerName);

        return player.getCurrentHand();
    }

    public List<ParticipantCurrentHand> getPlayersHand() {
        return players.values().stream()
                .map(Participant::getCurrentHand)
                .toList();
    }

    public boolean isPlayerBust(String playerName) {
        Player player = getPlayer(playerName);
        return player.isBust();
    }

    public ProfitResult getProfitResult(DealerStatus dealerStatus) {
        DealerProfit dealerProfit = new DealerProfit();
        PlayerProfits playerProfit = new PlayerProfits();

        players.values().forEach(player -> {
            PlayerStatus playerStatus = getPlayerStatus(player);
            Integer profit = ProfitCalculator.calculateBetAmount(dealerStatus, playerStatus);

            playerProfit.addPlayerProfit(new ParticipantProfit(player.getName(), profit));
            dealerProfit.increase(-profit);
        });

        return new ProfitResult(dealerProfit.getDealerProfit(dealerStatus.name()), playerProfit.getPlayerProfits());
    }

    private Player getPlayer(String playerName) {
        if(!players.containsKey(playerName)) {
            throw new IllegalArgumentException(ErrorMessage.NO_PLAYER_NAME.getMessage());
        }

        return players.get(playerName);
    }

    private PlayerStatus getPlayerStatus(Player player) {
        Integer betPrice = playerBet.get(player.getName());

        if(betPrice == null) {
            throw new IllegalArgumentException(ErrorMessage.NO_PLAYER_NAME.getMessage());
        }

        return new PlayerStatus(player.getName(), player.getScore(), betPrice, player.isBust(), player.isBlackJack());
    }
}
