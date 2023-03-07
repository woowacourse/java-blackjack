package balckjack.controller;

import balckjack.domain.Dealer;
import balckjack.domain.Player;
import balckjack.domain.Players;
import balckjack.domain.Referee;
import balckjack.domain.Result;
import balckjack.strategy.CardPicker;
import balckjack.util.Repeater;
import balckjack.view.InputView;
import balckjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    public void run(CardPicker cardPicker) {
        final Players players = Repeater.repeatIfError(this::inputPlayerNames,
            OutputView::printErrorMessage);
        final Dealer dealer = new Dealer();
        final Referee referee = new Referee();

        initializeGame(cardPicker, players, dealer);
        tryPlayersTurn(cardPicker, players, referee);
        tryDealerTurn(cardPicker, dealer, referee);
        reportResult(players, dealer, referee);
    }

    private void initializeGame(CardPicker cardPicker, Players players, Dealer dealer) {
        dealer.distributeCards(cardPicker);
        players.initHit(cardPicker);

        OutputView.printInitCardDeck(dealer, players);
    }

    private void tryPlayersTurn(CardPicker cardPicker, Players players, Referee referee) {
        for (Player player : players.getPlayers()) {
            tryPlayerTurn(referee, player, cardPicker);
        }
    }

    private void tryPlayerTurn(Referee referee, Player player, CardPicker cardPicker) {
        if (isContinueHit(player, cardPicker) && !isBurst(referee, player)) {
            tryPlayerTurn(referee, player, cardPicker);
        }
    }

    private boolean isContinueHit(Player player, CardPicker cardPicker) {
        if (askIfContinue(player) == Command.NO) {
            return false;
        }
        player.hit(cardPicker);
        OutputView.printParticipantCardDeck(player);
        return true;
    }

    private Command askIfContinue(Player player) {
        return Repeater.repeatIfError(() -> inputCommand(player),
            OutputView::printErrorMessage);
    }

    private boolean isBurst(Referee referee, Player player) {
        if (referee.isBurst(player.getCardDeck())) {
            OutputView.printBurstMessage();
            return true;
        }
        return false;
    }

    private Players inputPlayerNames() {
        return new Players(InputView.inputPlayerNames());
    }

    private Command inputCommand(Player player) {
        return Command.toCommand(InputView.inputReply(player.getName().getValue()));
    }

    private void tryDealerTurn(CardPicker cardPicker, Dealer dealer, Referee referee) {
        while (referee.calculateDeckScore(dealer.getCardDeck()) <= Referee.DEALER_HIT_NUMBER
            && referee.isBurst(dealer.getCardDeck())) {
            dealer.hit(cardPicker);
            OutputView.printDealerPickMessage(dealer);
        }
    }

    private void reportResult(Players players, Dealer dealer, Referee referee) {
        List<Result> results = referee.judgeResult(dealer, players);
        OutputView.printFinalCardDeck(dealer, players, referee);
        OutputView.printResult(referee.countDealerResult(results), dealer, players,
            results);
    }
}
