package config;

import controller.BlackJackController;
import domain.service.*;
import repository.CardRepository;
import repository.DealerRepository;
import repository.PlayerRepository;
import util.*;
import view.InputView;
import view.OutputView;
import view.View;

public class DIConfig {

    private PlayerRepository playerRepository = new PlayerRepository();
    private CardRepository cardRepository = new CardRepository();
    private DealerRepository dealerRepository = new DealerRepository();

    public BlackJackController blackJackController() {
        return new BlackJackController(
                blackJackService(),
                view()
        );
    }

    public PersonService personService() {
        return new PersonService(
                playerRepository(),
                dealerRepository()
        );
    }

    // service
    public BlackJackService blackJackService() {
        return new BlackJackService(
                personService(),
                cardDistributor(),
                judgementService()
        );
    }

    public JudgementService judgementService() {
        return new JudgementService(
                personService()
        );
    }

    public CardDistributor cardDistributor() {
        return new CardDistributor(
                personService(),
                cardFactory()
        );
    }

    public CardFactory cardFactory() {
        return new CardFactory(
                cardRepository(),
                cardNumberGenerator()
        );
    }

    public CardNumberGenerator cardNumberGenerator() {
        return new CardNumberGenerator(
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
    public View view() {
        return new View(
                inputView(),
                outputView()
        );
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
