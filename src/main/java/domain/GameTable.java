package domain;

import dto.GameResult;
import dto.GameStatus;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class GameTable {

    private final Queue<Participant> participants;

    public GameTable() {
        this.participants = new LinkedList<>();
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public List<GameStatus> initGameStatus() {
        List<GameStatus> gameStatuses = new ArrayList<>();
        for (Participant participant : participants) {
            GameStatus gameStatus = GameStatus.from(participant);
            gameStatuses.add(gameStatus);
        }

        return gameStatuses;
    }

    public List<GameStatus> endedGameStatus() {
        List<GameStatus> gameStatuses = new ArrayList<>();
        for (Participant participant : participants) {
            GameStatus gameStatus = GameStatus.from(participant);
            gameStatuses.add(gameStatus);
        }

        return gameStatuses;
    }

    public void playCurrentPlayer() {
        if (isPlayer()) {
            currentPlayer().draw();
        }
    }

    public void playDealer() {
        dealer().draw();
    }

    public GameStatus currentPlayerStatus() {
        return GameStatus.from(currentParticipant());
    }

    public Participant currentPlayer() {
        Participant participant = participants.peek();

        if (!participants.isEmpty() && participant.isDealer()) {
            participants.add(participants.poll());
        }

        return participants.peek();
    }

    public boolean isPlayer() {
        return !participants.isEmpty() && !participants.peek().isDealer();
    }

    public void rotateParticipant() {
        participants.add(participants.poll());
    }

    public List<GameResult> results() {
        List<Participant> players = selectPlayers();
        Participant dealer = dealer();
        return ScoreBoard.calculateGameResults(players, dealer);
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

    private List<Participant> selectPlayers() {
        return participants.stream().filter(participant -> !participant.isDealer())
                .collect(Collectors.toList());
    }

    private Participant dealer() {
        return participants.stream().filter(Participant::isDealer).findFirst().orElse(null);
    }

    private Participant currentParticipant() {
        return participants.peek();
    }
}
