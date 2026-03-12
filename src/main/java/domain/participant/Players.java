package domain.participant;

import domain.Bet;
import domain.Card;
import domain.Hand;
import domain.dto.GameScoreResultDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {

    private final Map<String, Player> players = new LinkedHashMap<>();

    public void register(String playerName, String betAmount) {
        players.put(playerName, new Player(playerName, new Hand(), betAmount));
    }

    public List<String> drawCardTo(String playerName, Card card) {
        Player targetPlayer = players.get(playerName);
        targetPlayer.receiveCard(card);
        return targetPlayer.showHand();
    }

    public List<GameScoreResultDto> getScoreResults() {
        return players.values().stream()
                .map(GameScoreResultDto::from)
                .toList();
    }

    public boolean canReceiveCard(String playerName) {
        return players.get(playerName).canReceiveCard();
    }

    public List<Player> getAll() {
        return players.values().stream().toList();
    }
}
