package service;

import repository.ParticipantRepository;
import repository.ScoreRepository;

public class BlackJackCommandService {

    private final ParticipantRepository participantRepository;
    private final ScoreRepository scoreRepository;

    public BlackJackCommandService(ParticipantRepository participantRepository, ScoreRepository scoreRepository) {
        this.participantRepository = participantRepository;
        this.scoreRepository = scoreRepository;
    }
}
