package balckjack.controller;

import balckjack.domain.Command;
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

    private static final int BURST_CODE = -1;
    private static final int DEALER_HIT_NUMBER = 16;

    public void run(CardPicker cardPicker) {
        final Players players = Repeater.repeatIfError(this::inputPlayerNames,
            OutputView::printErrorMessage);
        final Dealer dealer = new Dealer();
        final Referee referee = new Referee();

        initialSetting(cardPicker, players, dealer);
        tryPlayersTurn(cardPicker, players, referee);
        tryDealerTurn(cardPicker, dealer, referee);
        reportResult(players, dealer, referee);
    }

    private void initialSetting(CardPicker cardPicker, Players players, Dealer dealer) {
        dealer.initHit(cardPicker);
        players.initHit(cardPicker);

        OutputView.printInitCardDeck(dealer, players);
    }

    private void tryPlayersTurn(CardPicker cardPicker, Players players, Referee referee) {
        System.out.println();
        for (Player player : players.getPlayers()) {
            askPlayer(referee, player, cardPicker);
        }
    }

    private void askPlayer(Referee referee, Player player, CardPicker cardPicker) {
        while (isContinue(referee, player, cardPicker)) {
        }
    }

    private boolean isContinue(Referee referee, Player player, CardPicker cardPicker) {
        if (isReplyNo(player)) {
            return false;
        }
        player.hit(cardPicker);
        OutputView.printParticipantCardDeck(player);
        if (isBurst(referee, player)) {
            return false;
        }
        return true;
    }

    private boolean isReplyNo(Player player) {
        Command command = Repeater.repeatIfError(() -> inputCommand(player),
            OutputView::printErrorMessage);
        return command == Command.NO;
    }

    private boolean isBurst(Referee referee, Player player) {
        int score = referee.calculateDeckScore(player.getCardDeck());
        if (score == BURST_CODE) {
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
        while (referee.calculateDeckScore(dealer.getCardDeck()) <= DEALER_HIT_NUMBER
            && referee.calculateDeckScore(dealer.getCardDeck()) != BURST_CODE) {
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
