package controller;

import static util.Constants.COMMA_DELIMITER;
import static util.Constants.DEALER_NAME;

import domain.game.Game;
import domain.player.Participant;
import dto.InitialCardInfoResponseDto;
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
        Game game = initializeGame(names);
    }

    private List<String> inputGamblersInfo() {
        String name = inputView.askGamblerNames().name();
        return Parser.parse(name, COMMA_DELIMITER);
    }

    private Game initializeGame(List<String> names) {
        Game game = new Game(DEALER_NAME, names, 1);

        outputView.printInitialDeal(names);
        game.initializeGame();
        outputView.printInitialInfo(new InitialCardInfoResponseDto(
                game.getDealerName(),
                game.getDealerInitialInfo(),
                game.getGamblersHandInfo()
        ));

        return game;
    }
}