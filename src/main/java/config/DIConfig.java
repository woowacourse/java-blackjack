package config;

import controller.BlackJackController;
import domain.service.BlackJackService;
import repository.CardRepository;

public class DIConfig {

    private CardRepository cardRepository = new CardRepository();

    public BlackJackController blackJackController() {
        return new BlackJackController(blackJackService());
    }

    public BlackJackService blackJackService() {
        return new BlackJackService(cardRepository());
    }

    public CardRepository cardRepository() {
        return cardRepository;
    }
}
