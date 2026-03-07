package controller;

import static util.Constants.COMMA_DELIMITER;

import java.util.List;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> names = inputGamblersInfo();
    }
    
    private List<String> inputGamblersInfo() {
        String name = inputView.askGamblerNames().name();
        return Parser.parse(name, COMMA_DELIMITER);
    }
}