package domain;

import domain.strategy.RandomStrategy;
import dto.GameResult;
import dto.GameStatus;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameTable {

    private final Queue<Participant> participants;
    private final ScoreBoard scoreBoard;
    private final DrawStrategy drawStrategy;

    public GameTable() {
        this.participants = new LinkedList<>();
        this.scoreBoard = new ScoreBoard();
        this.drawStrategy = new RandomStrategy();
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public void addPlayer(String name) {
        participants.add(Player.of(name));
    }

//
//    public List<GameStatus> initGameStatus() {
//        List<GameStatus> gameStatuses = new ArrayList<>();
//        for (Participant participant : participants) {
//            gameStatuses.add(participant.status());
//        }
//
//        return gameStatuses;
//    }

    public List<GameStatus> endedGameStatus() {
        return scoreBoard.gameStatuses();
    }

    public void playCurrentPlayer() {
        if (isPlayerExist()) {
            currentPlayer().draw(drawStrategy);
        }
    }

    public void playDealer() {
        dealer().draw(drawStrategy);
    }
//
//    public GameStatus currentPlayerStatus() {
//        return currentParticipant().status();
//    }

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

//    public void recordResult() {
//        Participant current = participants.poll();
//        scoreBoard.record(current.status());
//    }

    public List<GameResult> result() {
        return scoreBoard.playerResults();
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
