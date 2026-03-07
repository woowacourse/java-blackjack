package domain;

import dto.GameResult;
import dto.GameStatus;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameTable {

    private final Queue<Participant> participants;
    private final ScoreBoard scoreBoard;

    public GameTable() {
        this.participants = new LinkedList<>();
        this.scoreBoard = new ScoreBoard();
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public List<GameStatus> initGameStatus() {
        List<GameStatus> gameStatuses = new ArrayList<>();
        for (Participant participant : participants) {
            gameStatuses.add(participant.status());
        }

        return gameStatuses;
    }

    public List<GameStatus> endedGameStatus() {
        return scoreBoard.gameStatuses();
    }

    public void playCurrentPlayer() {
        if (isPlayerExist()) {
            currentPlayer().draw();
        }
    }

    public void playDealer() {
        dealer().draw();
    }

    public GameStatus currentPlayerStatus() {
        return currentParticipant().status();
    }

    public Participant currentPlayer() {
        Participant participant = participants.peek();

        if (!participants.isEmpty() && !participant.isPlayer()) {
            participants.add(participants.poll());
        }

        return participants.peek();
    }

    public boolean isPlayerExist() {
        return !participants.isEmpty() && !hasOnlyDealer();
    }

    public void recordResult() {
        Participant current = participants.poll();
        scoreBoard.record(current.status());
    }

    public List<GameResult> result() {
        return scoreBoard.results();
    }

    public String currentPlayerName() {
        return currentPlayer().name();
    }

    public boolean isCurrentPlayerPlayable() {
        return currentPlayer().isPlayable();
    }

    public boolean isDealerPlayable() {
        return currentParticipant().isPlayable();
    }

    private Participant dealer() {
        return participants.stream().filter(p -> !p.isPlayer()).findFirst().orElse(null);
    }

    private boolean hasOnlyDealer() {
        return participants.stream().noneMatch(Participant::isPlayer);
    }

    private Participant currentParticipant() {
        return participants.peek();
    }
}
