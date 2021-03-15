package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.service.BlackJackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {

    private final BlackJackService blackJackService;

    public BlackJackController(final BlackJackService blackJackService) {
        this.blackJackService = blackJackService;
    }

    public void run() {
        initSeats();
        initBettings();
        printInitCards();
        distributeCards();
        cardStatus();
        gameResult();
    }

    private void initSeats() {
        try {
            blackJackService.initPlayers(requestNames());
            blackJackService.initDealer();
        } catch (IllegalArgumentException e) {
            OutputView.printIllegalArgumentError(e);
            initSeats();
        }
    }

    private void initBettings() {
        try {
            List<String> playerNames = blackJackService.getPlayersAsStrings();
            blackJackService.initBettings(InputView.getBettings(playerNames));
        } catch (NumberFormatException e) {
            OutputView.printNumberFormatExceptionError(e);
            initBettings();
        }
    }

    private void printInitCards() {
        OutputView.printInitSetting(blackJackService.getPlayersAsList());
        OutputView.printInitCards(blackJackService.getDealer(), blackJackService.getPlayersAsList());
    }

    private void distributeCards() {
        if (blackJackService.isDealerBlackJack()) {
            return;
        }
        List<Player> players = blackJackService.getPlayersAsList();
        for (Player player : players) {
            receivePlayerMoreCard(player);
        }
        receiveDealerMoreCard(blackJackService.getDealer());
    }

    private void cardStatus() {
        OutputView.printResult(blackJackService.getParticipantsAsList());
    }

    private void gameResult() {
        blackJackService.initGameResult();
        blackJackService.calculateProfits();
        OutputView.printSummary(blackJackService.getGameResult());
        OutputView.printProfits(blackJackService.getPlayersAsList());
    }

    private List<String> requestNames() {
        return InputView.getNames();
    }

    private void receivePlayerMoreCard(final Player player) {
        while (!player.isBust() && InputView.wantMoreCard(player)) {
            blackJackService.receiveMoreCard(player);
            OutputView.printParticipantCards(player);
        }
    }

    private void receiveDealerMoreCard(final Dealer dealer) {
        while (!dealer.isBust() && !dealer.doneReceiving()) {
            blackJackService.receiveMoreCard(dealer);
            OutputView.printDealerReceiveMessage();
        }
    }
}
