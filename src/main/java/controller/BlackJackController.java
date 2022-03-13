package controller;

import static model.Dealer.DEALER_NAME;

import dto.AllParticipatorsDto;
import java.util.List;
import java.util.function.Supplier;
import service.BlackJackService;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final BlackJackService service;

    public BlackJackController(BlackJackService service) {
        this.service = service;
    }

    public void run() {
        requestUntilValid(() -> initGame());
        requestUntilValid(() -> hitPlayers());
        requestUntilValid(() -> hitDealer());
        requestUntilValid(() -> match());
        requestUntilValid(() -> getCardsResults());
    }

    private void requestUntilValid(Runnable request) {
        boolean isInvalidRequest;
        do {
            isInvalidRequest = execute(request);
        } while(isInvalidRequest);
    }

    private boolean execute(Runnable request) {
        try {
            request.run();
            return false;
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception.getMessage());
            return true;
        }
    }

    private void initGame() {
        List<String> names = InputView.inputPlayerNames();
        AllParticipatorsDto allParticipatorsDto = service.initGame(names);
        OutputView.printInit(allParticipatorsDto);
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
        if (service.canReceiveCard(DEALER_NAME)) {
            service.hitDealer();
            OutputView.printHitDealer();
        }
    }

    private void match() {
        OutputView.printMatchResult(service.match());
    }

    private void getCardsResults() {
        OutputView.printResult(service.getAllCardsAndSums());
    }
}
