package blackjack.domain.participant;

import blackjack.domain.GameResult;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participants {
    private final List<Participant> participants;
    private int nowTurnIndex;

    public Participants(List<Participant> participants) {
        validateEmptyNames(participants);
        this.participants = participants;
        this.nowTurnIndex = 0;
    }

    private void validateEmptyNames(List<Participant> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 빈 이름이 있습니다.");
        }
    }

    public boolean isFinished() {
        return nowTurnIndex >= participants.size();
    }

    public void skipTurn() {
        nowTurnIndex++;
    }

    public Map<GameResult, Integer> getDealerGameResult(int dealerScore) {
        Map<GameResult, Integer> gameResult = new EnumMap<>(GameResult.class);
        for (Participant participant : participants) {
            GameResult result = GameResult.compare(dealerScore, participant.calculateScore());
            gameResult.put(result, gameResult.getOrDefault(result, 0) + 1);
        }
        return gameResult;
    }

    public Map<String, GameResult> getPlayersGameResult(int dealerScore) {
        HashMap<String, GameResult> playersGameResult = new HashMap<>();
        for (Participant participant : participants) {
            GameResult result = GameResult.compare(participant.calculateScore(), dealerScore);
            playersGameResult.put(participant.getName(), result);
        }
        return playersGameResult;
    }

    public Participant getCurrentPlayer() {
        return participants.get(nowTurnIndex);
    }

    public String getCurrentPlayerName() {
        return getCurrentPlayer().getName();
    }

    public List<Participant> getParticipants() {
        return participants;
    }

}
