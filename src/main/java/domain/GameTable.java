package domain;

import domain.vo.DealerWinningScore;
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

    public List<PlayerWinningInfo> playerWinningInfos() {
        return scoreBoard.playerWinningInfos();
    }
}
