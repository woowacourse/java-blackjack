package service;

import repository.ParticipantRepository;
import repository.ScoreRepository;

public class BlackJackQueryService {

    private final ParticipantRepository participantRepository;
    private final ScoreRepository scoreRepository;

    public BlackJackQueryService(ParticipantRepository participantRepository, ScoreRepository scoreRepository) {
        this.participantRepository = participantRepository;
        this.scoreRepository = scoreRepository;
    }
}
