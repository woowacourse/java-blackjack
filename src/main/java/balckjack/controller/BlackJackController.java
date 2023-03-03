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

    private final CardPicker cardPicker;

    public BlackJackController(CardPicker cardPicker) {
        this.cardPicker = cardPicker;
    }

    public void run() {
        final Players players = Repeater.repeatIfError(this::inputPlayerNames,
            OutputView::printErrorMessage);
        final Dealer dealer = new Dealer();
        Referee referee = new Referee();
        dealer.initHit(cardPicker);
        players.initHit(cardPicker);
        OutputView.printInitCardDeck(dealer, players);

        for (Player player : players.getPlayers()) {
            Command command;
            while (true) {
                command = Repeater.repeatIfError(() -> inputCommand(player),
                    OutputView::printErrorMessage);
                if (command == Command.NO) {
                    break;
                }
                player.hit(cardPicker);
                OutputView.printParticipantCardDeck(player);
                int score = referee.calculateDeckScore(player.getCardDeck());
                if (score == -1) {
                    OutputView.printBurstMessage();
                    break;
                }
            }
        }
        System.out.println();
        while (referee.calculateDeckScore(dealer.getCardDeck()) <= 16
            && referee.calculateDeckScore(dealer.getCardDeck()) != -1) {
            dealer.hit(cardPicker);
            OutputView.printDealerPickMessage();
        }
        List<Result> results = referee.judgeResult(dealer, players);
        OutputView.printFinalCardDeck(dealer, players, referee);
        OutputView.printResult(referee.countDealerResult(results), players,
            results);
    }

    private Players inputPlayerNames() {
        return new Players(InputView.inputPlayerNames());
    }

    private Command inputCommand(Player player) {
        return Command.toCommand(InputView.inputReply(player.getName().getValue()));
    }
}

