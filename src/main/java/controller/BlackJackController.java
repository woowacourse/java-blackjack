package controller;

import domain.service.BlackJackService;
import dto.InitialDto;
import util.InputValidator;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final BlackJackService blackJackService;
    private final InputView inputView;
    private final OutputView outputView;
    private final Parser<String> stringParser;

    public BlackJackController(BlackJackService blackJackService, InputView inputView, OutputView outputView, Parser<String> stringParser) {
        this.blackJackService = blackJackService;
        this.inputView = inputView;
        this.outputView = outputView;
        this.stringParser = stringParser;
    }

    public void run() {
        String inputPlayerNames = inputView.inputPlayerNames();
        InputValidator.validatePlayerNames(inputPlayerNames);
        InitialDto initialDto = blackJackService.createPlayer(stringParser.splitToDelimiter(inputPlayerNames, ","));

        outputView.outputInitialMessage(initialDto);
    }
}
