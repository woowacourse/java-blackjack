package controller;

import static model.Dealer.DEALER_NAME;

import dto.AllParticipatorsDto;
import java.util.List;
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
        AllParticipatorsDto allParticipatorsDto = service.initGame(names);
        OutputView.printInit(allParticipatorsDto);
    }

    public void hitPlayers() {
        List<String> names = service.getPlayerNames();
        for (String name : names) {
            hit(name);
        }
    }

    private void hit(String name) {
        while (service.canReceiveCard(name) && InputView.inputHitResponse(name)) {
            OutputView.printParticipatorHit(service.hitParticipatorOf(name));
        }
    }

    public void hitDealer() {
        if (service.canReceiveCard(DEALER_NAME)) {
            service.hitDealer();
            OutputView.printHitDealer();
        }
    }

    public void match() {
        OutputView.printMatchResult(service.match());
    }

    public void getCardsResults() {
        OutputView.printResult(service.getAllCardsAndSums());
    }
}
