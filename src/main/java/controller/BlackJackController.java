package controller;

import domain.Game;
import domain.amount.Amount;
import domain.amount.BetAmount;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Names;
import domain.participant.Player;
import domain.participant.Players;
import dto.ParticipantsDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.OutputView;

public class BlackJackController {

    private final InputController inputController;
    private final OutputView outputView;

    public BlackJackController(final InputController inputController, final OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        final Names names = inputController.getNames();
        final Dealer dealer = new Dealer();
        final Players players = registerPlayers(names);

        final Game game = new Game(dealer, players);

        initHands(game);
        dealWithPlayers(game);
        dealerTurn(game);

        printFinalResult(players, dealer);
    }

    private void dealerTurn(final Game game) {
        game.dealerTurn(outputView::printDealerTurnMessage);
    }

    private Players registerPlayers(final Names names) {
        final List<Player> playerList = new ArrayList<>();
        for (final Name name : names.getPlayerNames()) {
            final BetAmount betAmount = inputController.getBetAmount(name.getValue());
            playerList.add(new Player(name, betAmount));
        }
        return new Players(playerList);
    }

    private void dealWithPlayers(final Game game) {
        if (game.isAlreadyEnd()) {
            outputView.printDealerBlackJack();
            return;
        }
        game.checkingPlayersBlackJack(outputView::printBlackJack);
        game.dealWithPlayers(
                inputController::getAnswer,
                outputView::printHands,
                outputView::printPlayerEndMessage);
    }

    private void initHands(final Game game) {
        game.initHands(outputView::printStartDeal);
    }

    private void printFinalResult(final Players players, final Dealer dealer) {
        outputView.printHandsResult(ParticipantsDto.of(dealer, players));
        final Map<Player, Amount> finalResult = players.calculateResult(dealer);
        final Amount dealerAmount = dealer.calculateRevenue(finalResult);
        outputView.printGameResult(finalResult, dealerAmount);
    }
}
