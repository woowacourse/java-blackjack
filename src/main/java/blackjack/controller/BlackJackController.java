package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.ResultStatistics;
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
        initBlackJackGame();
        distributeCards();
        cardStatus();
        gameResult();
    }

    private void initBlackJackGame() {
        try {
            blackJackService.initPlayers(requestNames());
            blackJackService.initDealer();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            initBlackJackGame();
            return;
        }
        OutputView.printInitSetting(blackJackService.getPlayersAsList());
        OutputView.printInitCards(blackJackService.getDealer(), blackJackService.getPlayers());
    }

    private void distributeCards() {
        if (blackJackService.isDealerBlackJack()) {
            return;
        }

        Players players = blackJackService.getPlayers();
        for (Player player : players.getPlayersAsList()) {
            receivePlayerMoreCard(player);
        }
        receiveDealerMoreCard(blackJackService.getDealer());
    }

    private void cardStatus() {
        OutputView.printResult(blackJackService.getParticipantsAsList());
    }

    private void gameResult() {
        ResultStatistics resultStatistics = new ResultStatistics(blackJackService.getPlayers(), blackJackService.getDealer());
        OutputView.printSummary(resultStatistics);
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
