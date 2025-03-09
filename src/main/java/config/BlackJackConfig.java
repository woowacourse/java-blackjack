package config;

import static factory.BlackJackCreator.createCardBundle;

import controller.BlackJackController;
import domain.card.CardDeck;
import view.InputView;
import view.OutputView;

public class BlackJackConfig {

    public static BlackJackController createBlackJackController() {
        return new BlackJackController(new InputView(), new OutputView(),
            new CardDeck(createCardBundle().getAllCards()));
    }
}
