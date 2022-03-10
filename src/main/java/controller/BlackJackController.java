package controller;

import java.util.List;
import dto.InitGameDto;
import dto.NamesDto;
import service.BlackJackService;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final BlackJackService service;
    public BlackJackController(BlackJackService service) {
        this.service = service;
    }

    public void initGame() {
        List<String> names = InputView.inputPlayerNames();
        NamesDto namesDto = new NamesDto(names);
        InitGameDto initGameDto = service.initGame(namesDto);
        OutputView.printInit(initGameDto);
    }
}
