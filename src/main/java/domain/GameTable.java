package domain;

import domain.vo.NameAndCardInfos;
import java.util.List;

public class GameTable {

    private final Participants participants;
    private final ScoreBoard scoreBoard;

    private GameTable(Participants participants, ScoreBoard scoreBoard) {
        this.participants = participants;
        this.scoreBoard = scoreBoard;
    }

    public static GameTable setupGame(List<String> playerNames, DrawStrategy drawStrategy) {
        return new GameTable(Participants.from(playerNames, drawStrategy), new ScoreBoard());
    }

    public List<String> allPlayerNames() {
        return participants.allPlayerNames();
    }

    public void allParticipantsDrawInitialCards() {
        participants.allParticipantsDrawInitialCards();
    }

    public NameAndCardInfos dealerCardsInfo() {
        return participants.dealerCardInfos();
    }

    public List<NameAndCardInfos> allPlayerCardInfos() {
        return participants.allPlayerCardInfos();
    }

    public NameAndCardInfos currentPlayerCardInfos() {
        return participants.currentPlayerCardInfos();
    }

    public String currentPlayerName() {
        return participants.currentPlayerName();
    }

    public void currentPlayerDrawCard() {
        participants.currentPlayerDrawCard();
    }

    public boolean isCurrentPlayerPlayable() {
        return participants.isCurrentPlayerPlayable();
    }

    public boolean hasWaitingPlayers() {
        return participants.hasWaitingPlayers();
    }

    public void recordCurrentGameResult() {
        PlayedGameResult playedGameResult = participants.currentPlayersResult();
        scoreBoard.record(playedGameResult);
    }

    public boolean isDealerPlayable() {
        return participants.isDealerPlayable();
    }

    public void dealerDrawCard() {
        participants.dealerDrawCard();
    }

    public void recordDealerGameResult() {
        PlayedGameResult playedGameResult = participants.dealerResult();
        scoreBoard.record(playedGameResult);
    }

    public PlayedGameResult dealerResult() {
        return scoreBoard.dealerGameResult();
    }

    public List<PlayedGameResult> playerResults() {
        return scoreBoard.playerGameResults();
    }

    public DealerWinningScore dealerWinningStatistics() {
        return scoreBoard.dealerWinningScore();
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
//
//    public List<GameStatus> endedGameStatus() {
//        return scoreBoard.gameStatuses();
//    }
//
//    public void playCurrentPlayer() {
//        if (isPlayerExist()) {
//            currentPlayer().draw(drawStrategy);
//        }
//    }
//
//    public void playDealer() {
//        dealer().draw(drawStrategy);
//    }
//
//    public GameStatus currentPlayerStatus() {
//        return currentParticipant().status();
//    }
//
//    public Participant currentPlayer() {
//        Participant participant = participants.peek();
//
//        if (!participants.isEmpty() && !participant.isPlayer()) {
//            participants.add(participants.poll());
//        }
//
//        return participants.peek();
//    }
//
//    public boolean isPlayerExist() {
//        return !participants.isEmpty() && !hasOnlyDealer();
//    }

//    public void recordResult() {
//        Participant current = participants.poll();
//        scoreBoard.record(current.status());
//    }
//
//    public List<GameResult> result() {
//        return scoreBoard.playerResults();
//    }
//
//    public String currentPlayerName() {
//        return currentPlayer().name();
//    }
//
//    public boolean isCurrentPlayerPlayable() {
//        return currentPlayer().isPlayable();
//    }
//
//    public boolean isDealerPlayable() {
//        return currentParticipant().isPlayable();
//    }
//
//    private Participant dealer() {
//        return participants.stream().filter(p -> !p.isPlayer()).findFirst().orElse(null);
//    }
//
//    private boolean hasOnlyDealer() {
//        return participants.stream().noneMatch(Participant::isPlayer);
//    }
//
//    private Participant currentParticipant() {
//        return participants.peek();
//    }
//
//    public Participant dealersCard() {
//        return participants.stream()
//                .filter(participant -> !participant.isPlayer())
//                .findAny();
//    }

//    public NameAndCardInfos dealerCardsInfo() {
//        return participants.dealerCardInfos();
//    }
//
//    public NameAndCardInfos currentPlayersCardInfos() {
//        return participants.currentPlayerCardInfos();
//    }

//    public List<>
}
