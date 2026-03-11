package service;

import domain.ParticipantFactory;
import domain.Participants;
import java.util.List;
import repository.ParticipantRepository;
import repository.ScoreRepository;

public class BlackJackCommandService {

//    private final GameTableRepository gameTableRepository;
//    private final BlackJackFactory blackJackFactory;
//
//    public BlackJackCommandService(GameTableRepository gameTableRepository,
//                                   BlackJackFactory blackJackFactory) {
//        this.gameTableRepository = gameTableRepository;
//        this.blackJackFactory = blackJackFactory;
//    }

    private final ParticipantRepository participantRepository;
    private final ScoreRepository scoreRepository;

    public BlackJackCommandService(ParticipantRepository participantRepository,
                                   ScoreRepository scoreRepository,
                                   ParticipantFactory participantFactory) {
        this.participantRepository = participantRepository;
        this.scoreRepository = scoreRepository;

        setupDealerToParticipantsFrom(participantFactory);
    }

    private void setupDealerToParticipantsFrom(ParticipantFactory participantFactory) {
        Participants participants = participantFactory.onlyDealer();
        participantRepository.save(participants);
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

//    public void recordCurrentGameResult() {
//        scoreRepository.recordCurrentGameResult();
//    }

    public void dealerDrawCard() {
        participantRepository.dealerDrawCard();
    }

//    public void recordDealerGameResult() {
//        scoreRepository.recordDealerGameResult();
//    }
}
