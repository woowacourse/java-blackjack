package controller;

import static model.participator.Dealer.DEALER_NAME;

import dto.AllParticipatorsDto;
import java.util.List;
import java.util.stream.Collectors;
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
        matchFirstTurn();
        hitPlayers();
        hitDealer();
        getCardsResults();
        getMatchResults();
    }

    private void initGame() {
        List<String> names = InputView.inputPlayerNames();
        List<Long> bettingAmounts = names.stream()
                .map(InputView::inputBetting)
                .collect(Collectors.toList());
        AllParticipatorsDto allParticipatorsDto = service.initGame(names, bettingAmounts);
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
