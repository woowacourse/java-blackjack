package controller;

import static model.participator.Dealer.DEALER_NAME;

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

    public void run() {
        initGame();
        betPlayers();
        drawFirstTurn();
        matchFirstTurn();
        hitPlayers();
        hitDealer();
        getCardsResults();
        getMatchResults();
    }

    private void initGame() {
        List<String> names = InputView.inputPlayerNames();
        service.initGame(names);
    }

    private void betPlayers() {
        for (String name : service.getPlayerNames()) {
            service.betByPlayerName(name, InputView.inputBetting(name));
        }
    }

    private void drawFirstTurn() {
        AllParticipatorsDto allParticipatorsDto = service.drawFirstTurn();
        OutputView.printInit(allParticipatorsDto);
    }

    private void matchFirstTurn() {
        service.matchFirstTurn();
    }

    private void hitPlayers() {
        List<String> names = service.getPlayerNames();
        for (String name : names) {
            hit(name);
        }
    }

    private void hit(String name) {
        while (service.canReceiveCard(name) && InputView.inputHitResponse(name)) {
            OutputView.printParticipatorHit(service.hitPlayerOf(name));
        }
    }

    private void hitDealer() {
        while (service.canReceiveCard(DEALER_NAME)) {
            service.hitDealer();
            OutputView.printHitDealer();
        }
    }

    private void getCardsResults() {
        OutputView.printResult(service.getAllCardsAndSums());
    }

    private void getMatchResults() {
        OutputView.printMatchResult(service.matchLastTurn());
    }
}
