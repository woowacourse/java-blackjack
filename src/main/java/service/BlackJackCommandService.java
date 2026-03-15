package service;

import dto.PlayedGameResult;
import domain.gameplaying.Participants;
import domain.result.ScoreBoard;
import java.util.List;

public class BlackJackCommandService {

    private final Participants participants;
    private final ScoreBoard scoreBoard;

    public BlackJackCommandService(Participants participants,
                                   ScoreBoard scoreBoard) {
        this.participants = participants;
        this.scoreBoard = scoreBoard;
    }

    public void setupPlayers(List<String> playerNames) {
        participants.join(playerNames);
    }

    public void distributeInitialCards() {
        participants.allParticipantsDrawInitialCards();
    }

    public void currentPlayerDrawsCard() {
        participants.currentPlayerDrawCard();
    }

    public void recordCurrentGameResult() {
        PlayedGameResult currentPlayerResult = participants.currentPlayerResult();
        scoreBoard.record(currentPlayerResult);
    }

    public void dealerDrawCard() {
        participants.dealerDrawCard();
    }

    public void recordDealerGameResult() {
        PlayedGameResult dealerResult = participants.dealerResult();
        scoreBoard.recordDealerResult(dealerResult);
    }
}
