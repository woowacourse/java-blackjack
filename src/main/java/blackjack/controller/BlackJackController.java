package blackjack.controller;

import blackjack.domain.Command;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Referee;
import blackjack.domain.Result;
import blackjack.strategy.CardPicker;
import blackjack.util.Repeater;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private static final int BURST_CODE = -1;
    private static final int DEALER_HIT_NUMBER = 16;

    private final CardPicker cardPicker;

    public BlackJackController(CardPicker cardPicker) {
        this.cardPicker = cardPicker;
    }

    public void run() {
        final Players players = Repeater.repeatIfError(this::inputPlayerNames,
            OutputView::printErrorMessage);
        final Dealer dealer = new Dealer();
        final Referee referee = new Referee();

        init(players, dealer);

        for (Player player : players.getPlayers()) {
            askPlayer(referee, player);
        }
        System.out.println();
        while (referee.calculateDeckScore(dealer.getCardDeck()) <= DEALER_HIT_NUMBER
            && referee.calculateDeckScore(dealer.getCardDeck()) != BURST_CODE) {
            dealer.hit(cardPicker);
            OutputView.printDealerPickMessage(dealer);
        }
        List<Result> results = referee.judgeResult(dealer, players);
        OutputView.printFinalCardDeck(dealer, players, referee);
        OutputView.printResult(referee.countDealerResult(results), dealer, players,
            results);
    }

    private void init(Players players, Dealer dealer) {
        dealer.initHit(cardPicker);
        players.initHit(cardPicker);
        OutputView.printInitCardDeck(dealer, players);
    }

    private void askPlayer(Referee referee, Player player) {
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
            if (score == BURST_CODE) {
                OutputView.printBurstMessage();
                break;
            }
        }
    }

    private Players inputPlayerNames() {
        return new Players(InputView.inputPlayerNames());
    }

    private Command inputCommand(Player player) {
        return Command.toCommand(InputView.inputReply(player.getName().getValue()));
    }
}

