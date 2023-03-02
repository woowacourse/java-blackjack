package controller;

import domain.Players;
import view.InputView;
import view.OutputView;

public class Controller {
    InputView inputView;
    OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(){
        Players players = new Players(inputView.readPlayerNames());

    }

}
