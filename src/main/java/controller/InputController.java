package controller;

import domain.Answer;
import domain.BetAmount;
import domain.participant.Names;
import exception.CustomException;
import java.util.List;
import view.InputView;
import view.OutputView;

public class InputController {

    private final InputView inputView;
    private final OutputView outputView;

    public InputController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public Names getNames() {
        Names names;
        do {
            names = readNames();
        } while (names == null);
        return names;
    }

    public Answer getAnswer(String name) {
        Answer answer;
        do {
            answer = readAnswer(name);
        } while (answer == null);
        return answer;
    }

    public BetAmount getBetAmount(String name) {
        BetAmount betAmount;
        do {
            betAmount = readBetAmount(name);
        } while (betAmount == null);
        return betAmount;
    }

    private Names readNames() {
        try {
            List<String> rawNames = inputView.readNames();
            return Names.from(rawNames);
        } catch (CustomException exception) {
            outputView.printException(exception.getErrorCode());
            return null;
        }
    }

    private Answer readAnswer(String name) {
        try {
            String value = inputView.readAnswer(name);
            return Answer.from(value);
        } catch (CustomException exception) {
            outputView.printException(exception.getErrorCode());
            return null;
        }
    }

    private BetAmount readBetAmount(final String name) {
        try {
            Long value = inputView.readBetAmount(name);
            return new BetAmount(value);
        } catch (CustomException exception) {
            outputView.printException(exception.getErrorCode());
            return null;
        }
    }
}
