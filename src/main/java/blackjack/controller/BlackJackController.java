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
        final Players players = Repeater.repeatIfError(this::inputPlayerNames, OutputView::printErrorMessage);
        final Dealer dealer = new Dealer();
        final Referee referee = new Referee();

        init(players, dealer);
        askPlayers(players, referee);
        OutputView.println();
        hitCardByDealer(dealer, referee);
        printFinal(players, dealer, referee);
    }

    private void printFinal(Players players, Dealer dealer, Referee referee) {
        List<Result> results = referee.judgeResult(dealer, players);
        OutputView.printFinalCardDeckAndScore(dealer, players, referee);
        OutputView.printResult(referee.countDealerResult(results), dealer, players, results);
    }

    private void hitCardByDealer(Dealer dealer, Referee referee) {
        int dealerScore = referee.calculateDeckScore(dealer.getCardDeck());

        while (isContinueToHit(dealerScore)) {
            dealer.hit(cardPicker);
            dealerScore = referee.calculateDeckScore(dealer.getCardDeck());
            OutputView.printDealerPickMessage(dealer);
        }
    }

    private boolean isContinueToHit(int dealerScore) {
        return dealerScore <= DEALER_HIT_NUMBER && dealerScore != BURST_CODE;
    }

    private void askPlayers(Players players, Referee referee) {
        for (Player player : players.getPlayers()) {
            askPlayer(referee, player);
        }
    }

    private void init(Players players, Dealer dealer) {
        dealer.initHit(cardPicker);
        players.initHit(cardPicker);
        OutputView.printInitCardDeck(dealer, players);
    }

    private void askPlayer(Referee referee, Player player) {
        Command command = Command.CONTINUE;
        int score = 0;

        while (isContinueToAsk(command, score)) {
            command = Repeater.repeatIfError(() -> inputCommand(player), OutputView::printErrorMessage);
            hitByCommand(player, command);
            OutputView.printPlayerCardDeck(player);
            score = calculateScore(referee, player);
        }
    }

    private int calculateScore(Referee referee, Player player) {
        int score = referee.calculateDeckScore(player.getCardDeck());

        if (BURST_CODE == score) {
            OutputView.printBurstMessage();
        }
        return score;
    }

    private void hitByCommand(Player player, Command command) {
        if (Command.isContinue(command)) {
            player.hit(cardPicker);
        }
    }

    private boolean isContinueToAsk(Command command, int score) {
        return Command.isContinue(command) && BURST_CODE != score;
    }

    private Players inputPlayerNames() {
        return new Players(InputView.inputPlayerNames());
    }

    private Command inputCommand(Player player) {
        return Command.toCommand(InputView.inputReply(player.getName().getValue()));
    }
}
