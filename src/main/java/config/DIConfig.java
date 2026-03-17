package config;

import controller.BlackJackController;
import domain.model.Dealer;
import domain.model.PlayerBettings;
import domain.model.Players;
import domain.service.*;
import repository.CardRepository;
import util.*;
import view.InputView;
import view.OutputView;
import view.View;

public class DIConfig {

    private final CardRepository cardRepository = new CardRepository();
    private final Players players = new Players();
    private final PlayerBettings playerBettings = new PlayerBettings();

    public BlackJackController blackJackController() {
        return new BlackJackController(
                blackJackService(),
                view()
        );
    }

    // service
    public BlackJackService blackJackService() {
        return new BlackJackService(
                judgementService(),
                players(),
                playerBettings(),
                dealer()
        );
    }

    public JudgementService judgementService() {
        return new JudgementService(playerBettings());
    }

    public CardDistributor cardDistributor() {
        return new CardDistributor(
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

    public CardRepository cardRepository() {
        return cardRepository;
    }

    public Players players() {
        return players;
    }

    public Dealer dealer() {
        return new Dealer(cardDistributor());
    }

    public PlayerBettings playerBettings() {
        return playerBettings;
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
