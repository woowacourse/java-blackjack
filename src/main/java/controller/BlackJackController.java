package controller;

import java.util.List;
import dto.AllParticipatorsDto;
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
        AllParticipatorsDto allParticipatorsDto = service.initGame(namesDto);
        OutputView.printInit(allParticipatorsDto);
    }

    public void hitPlayers() {
        List<String> names = service.getPlayerNames();
        for (String name : names) {
            hit(name);
        }
    }

    private void hit(String name) {
        boolean decidedToHit;
        do {
            decidedToHit = service.canReceiveCard(name) && InputView.inputHitResponse(name);
            OutputView.printParticipatorHit(decidedToHit, service.tryToHit(decidedToHit, name));
        } while (decidedToHit);
    }

    public void hitDealer() {
        OutputView.printHitDealer(service.tryToHitForDealer());
    }

    public void match() {
        OutputView.printMatchResult(service.match());
    }
}
