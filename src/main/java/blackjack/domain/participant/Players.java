package blackjack.domain.participant;

import blackjack.domain.GameResult;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> players;
    private int nowTurnIndex;

    public Players(List<Player> players) {
        validateEmptyNames(players);
        this.players = players;
        this.nowTurnIndex = 0;
    }

    private void validateEmptyNames(List<Player> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 이름이 있습니다.");
        }
    }

    public boolean isFinished() {
        return nowTurnIndex >= players.size();
    }

    public void skipTurn() {
        nowTurnIndex++;
    }

    public Map<GameResult, Integer> getDealerGameResult(int dealerScore) {
        Map<GameResult, Integer> gameResult = new EnumMap<>(GameResult.class);
        for (Participant participant : players) {
            GameResult result = GameResult.compareScore(dealerScore, participant.calculateScore());
            gameResult.put(result, gameResult.getOrDefault(result, 0) + 1);
        }
        return gameResult;
    }

    public Map<String, GameResult> getPlayersGameResult(int dealerScore) {
        HashMap<String, GameResult> playersGameResult = new HashMap<>();
        for (Participant participant : players) {
            GameResult result = GameResult.compareScore(participant.calculateScore(), dealerScore);
            playersGameResult.put(participant.getName(), result);
        }
        return playersGameResult;
    }

    public Player getCurrentPlayer() {
        return players.get(nowTurnIndex);
    }

    public String getCurrentPlayerName() {
        return getCurrentPlayer().getName();
    }

    public List<Player> getPlayers() {
        return players;
    }

}
