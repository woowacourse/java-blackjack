package config;

import controller.BlackJackController;
import domain.service.BlackJackService;
import domain.service.CardDistributor;
import domain.service.CardFactory;
import domain.service.JudgementService;
import repository.CardRepository;
import repository.DealerRepository;
import repository.PlayerRepository;
import util.*;
import view.InputView;
import view.OutputView;

public class DIConfig {

    private PlayerRepository playerRepository = new PlayerRepository();
    private CardRepository cardRepository = new CardRepository();
    private DealerRepository dealerRepository = new DealerRepository();

    public BlackJackController blackJackController() {
        return new BlackJackController(
                blackJackService(),
                inputView(),
                outputView()
        );
    }

    // service

    public BlackJackService blackJackService() {
        return new BlackJackService(
                playerRepository(),
                dealerRepository(),
                cardDistributor(),
                judgementService()
        );
    }

    public JudgementService judgementService() {
        return new JudgementService(
                playerRepository(),
                dealerRepository()
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
                randomCardGenerator()
        );
    }

    public RandomCardGenerator randomCardGenerator() {
        return new RandomCardGenerator(
                randomRankNumberGenerator(),
                randomShapeNumberGenerator()
        );
    }

    // repository

    public PlayerRepository playerRepository() {
        return playerRepository;
    }

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


    // view
    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
