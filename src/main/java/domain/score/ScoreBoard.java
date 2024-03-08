package domain.score;

import domain.Name;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreBoard {

    private final Score dealerScore = new Score();
    private final Map<Name, Status> playerStatus;

    private ScoreBoard(Map<Name, Status> playerStatus) {
        this.playerStatus = playerStatus;
    }

    public static ScoreBoard from(List<Name> names) {
        Map<Name, Status> scoreBoard = names.stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> Status.TIE
                ));
        return new ScoreBoard(scoreBoard);
    }

    public void updatePlayerScore(Name name, Status status) {
        playerStatus.put(name, status);
    }

    public void updateDealerScore(Status status) {
        dealerScore.increaseScore(status);
    }

    public Map<Name, Status> getPlayerScore() {
        return Collections.unmodifiableMap(playerStatus);
    }

    public Score getDealerScore() {
        return dealerScore;
    }
}
