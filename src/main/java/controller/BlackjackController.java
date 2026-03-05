package controller;

import dto.PlayersDto;
import java.util.List;
import service.BlackjackService;
import util.Parser;
import view.InputView;

public class BlackjackController {

    private final InputView inputView = new InputView();
    private final BlackjackService blackjackService = new BlackjackService();


    public void start() {

    }

    private PlayersDto inputPlayers() {
        List<String> input = Parser.parseInput(inputView.inputPlayers(), ",");
        return blackjackService.createPlayers(input);
    }

}
