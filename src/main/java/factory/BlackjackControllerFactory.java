package factory;

import controller.BlackjackController;
import domain.card.CardMachine;
import domain.game.BlackjackGameManager;
import domain.game.BlackjackJudge;
import view.InputView;
import view.OutputView;

public class BlackjackControllerFactory {

    public BlackjackController blackjackController() {
        return new BlackjackController(inputView(), outputView());
    }

    public BlackjackGameManager blackjackGameManager() {
        return new BlackjackGameManager(cardMachine(), blackjackJudge());
    }

    public CardMachine cardMachine() {
        return new CardMachine();
    }

    public BlackjackJudge blackjackJudge() {
        return new BlackjackJudge();
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
