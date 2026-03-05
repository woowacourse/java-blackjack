package config;

import controller.BlackJackController;
import domain.service.BlackJackService;
import domain.service.CardDistributor;
import domain.service.CardFactory;
import repository.CardRepository;
import repository.DealerRepository;
import util.NumberGenerator;
import util.RandomRankNumberGenerator;
import util.RandomShapeNumberGenerator;

public class DIConfig {

    private CardRepository cardRepository = new CardRepository();
    private DealerRepository dealerRepository = new DealerRepository();

    public BlackJackController blackJackController() {
        return new BlackJackController(blackJackService());
    }

    // service

    public BlackJackService blackJackService() {
        return new BlackJackService(
                cardRepository(),
                cardDistributor()
        );
    }


    public CardDistributor cardDistributor() {
        return new CardDistributor(
                dealerRepository(),
                cardFactory()
        );
    }

    public CardFactory cardFactory() {
        return new CardFactory(
                cardRepository(),
                randomRankNumberGenerator(),
                randomShapeNumberGenerator()
        );
    }

    // repository

    public CardRepository cardRepository() {
        return cardRepository;
    }

    public DealerRepository dealerRepository() {
        return dealerRepository;
    }

    // util
    public NumberGenerator randomRankNumberGenerator() {
        return new RandomRankNumberGenerator();
    }

    public NumberGenerator randomShapeNumberGenerator() {
        return new RandomShapeNumberGenerator();
    }
}
