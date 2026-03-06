package domain;

import dto.GameStatus;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameTable {

    private final ScoreBoard scoreBoard;
    private final Queue<Participant> participants;

    public GameTable() {
        this.scoreBoard = new ScoreBoard();
        this.participants = new LinkedList<>();
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

    public void playCurrentPlayer() {
        if(isPlayerExist()) {
            Participant current = participants.peek();
            current.draw();
        }
    }

    public boolean isPlayerExist() {
        return !participants.isEmpty() || hasOnlyDealer();
    }

    private boolean hasOnlyDealer() {
        return participants.stream().anyMatch(p -> !p.isPlayer());
    }

    public GameStatus currentPlayerStatus() {
        Participant current = participants.peek();
        return current.status();
    }

    public void recordResult() {
        Participant current = participants.poll();
        scoreBoard.record(current.status());

    }
}
