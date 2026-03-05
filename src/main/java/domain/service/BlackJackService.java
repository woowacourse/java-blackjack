package domain.service;

import repository.CardRepository;

public class BlackJackService {

    private final CardRepository cardRepository;

    public BlackJackService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

}
