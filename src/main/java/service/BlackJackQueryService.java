package service;

import repository.GameTableRepository;

public class BlackJackQueryService {

    private final GameTableRepository gameTableRepository;

    public BlackJackQueryService(GameTableRepository gameTableRepository) {
        this.gameTableRepository = gameTableRepository;
    }
}
