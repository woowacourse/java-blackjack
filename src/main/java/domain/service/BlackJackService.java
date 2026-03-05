package domain.service;

import repository.CardRepository;

public class BlackJackService {

    private final CardRepository cardRepository;
    private final CardDistributor cardDistributor;

    public BlackJackService(CardRepository cardRepository, CardDistributor cardDistributor) {
        this.cardRepository = cardRepository;
        this.cardDistributor = cardDistributor;
    }

}
