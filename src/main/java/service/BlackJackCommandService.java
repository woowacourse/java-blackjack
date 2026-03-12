package service;

import domain.common.PlayedGameResult;
import domain.gameplaying.DrawStrategy;
import domain.gameplaying.Participants;
import domain.result.ScoreBoard;
import java.util.List;
import repository.ParticipantRepository;
import repository.ScoreRepository;

public class BlackJackCommandService {

    private final ParticipantRepository participantRepository;
    private final ScoreRepository scoreRepository;

    public BlackJackCommandService(ParticipantRepository participantRepository,
                                   ScoreRepository scoreRepository,
                                   DrawStrategy drawStrategy) {
        this.participantRepository = participantRepository;
        this.scoreRepository = scoreRepository;

        setupWith(drawStrategy);
    }

    private void setupWith(DrawStrategy drawStrategy) {
        participantRepository.setup(Participants.onlyDealer(drawStrategy));
        scoreRepository.setup(new ScoreBoard());
    }

    public void setupPlayers(List<String> playerNames) {
        participantRepository.updateParticipantsWith(playerNames);
    }

    public void distributeInitialCards() {
        participantRepository.distributeInitialCards();
    }

    public void currentPlayerDrawCard() {
        participantRepository.currentPlayerDrawCard();
    }

    public void recordCurrentGameResult() {
        PlayedGameResult currentPlayerResult = participantRepository.getCurrentPlayerResult();
        scoreRepository.recordCurrentGameResult(currentPlayerResult);
    }

    public void dealerDrawCard() {
        participantRepository.dealerDrawCard();
    }

    public void recordDealerGameResult() {
        PlayedGameResult dealerResult = participantRepository.getDealerResult();
        scoreRepository.recordDealerGameResult(dealerResult);
    }
}
