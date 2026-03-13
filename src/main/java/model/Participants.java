package model;

import constant.ErrorMessage;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import model.dto.DealerStatus;
import model.dto.ParticipantWinning;
import model.dto.PlayerResult;
import model.dto.PlayerWinning;

public class Participants {
    private final Dealer dealer = new Dealer();
    private final Map<String, Player> players = new ConcurrentHashMap<>();

    public void addPlayer(Player player) {
        if(players.containsKey(player.getName())) {
            throw new IllegalArgumentException((ErrorMessage.DUPLICATED_NAME.getMessage()));
        }
        players.put(player.getName(), player);
    }

    public List<String> getPlayerNames() {
        return List.copyOf(players.keySet());
    }

    public void addBet(String playerName, BetPrice bet) {
        Player player = getPlayer(playerName);

        player.setBetAmount(bet);
    }

    public void drawDealer(Card card) {
        dealer.addCard(card);
    }

    public void drawPlayer(String playerName, Card card) {
        Player player = getPlayer(playerName);

        player.addCard(card);
    }

    public PlayerResult getPlayerResult(String playerName) {
        Player player = getPlayer(playerName);
        return player.getResult();
    }

    public PlayerResult getDealerResult() {
        return dealer.getResult();
    }

    public String getDealerFirstCard() {
        return dealer.getFirstCard();
    }

    public boolean isPlayerBust(String playerName) {
        Player player = getPlayer(playerName);

        return player.isBust();
    }

    public boolean isDealerCanDraw() {
        return dealer.canDraw();
    }

    public List<PlayerResult> getPlayerResults() {
        return List.copyOf(players.values().stream().map(Participant::getResult).toList());
    }

    public ParticipantWinning getGameResult() {
        DealerWinning dealerWinning = new DealerWinning();
        PlayersWinning playersWinning = new PlayersWinning();

        DealerStatus dealerStatus = dealer.getDealerStatus();

        players.values().forEach(player -> {
           PlayerWinning result = player.getBetResult(dealerStatus);
           playersWinning.add(result);
           dealerWinning.increase(-result.profit());
        });

        return new ParticipantWinning(dealerWinning, playersWinning);
    }

    private Player getPlayer(String playerName) {
        if(!players.containsKey(playerName)) {
            throw new IllegalArgumentException("없");
        }
        return players.get(playerName);
    }

}
