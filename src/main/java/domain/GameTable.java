package domain;

import dto.GameStatus;
import java.util.ArrayList;
import java.util.List;

public class GameTable {

    private final List<Participant> participants;

    public GameTable() {
        this.participants = new ArrayList<>();
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public List<GameStatus> gameStatus() {
        List<GameStatus> gameStatuses = new ArrayList<>();
        for (Participant participant : participants) {
            gameStatuses.add(participant.status());
        }

        return gameStatuses;
    }

}
