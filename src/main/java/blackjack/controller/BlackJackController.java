package blackjack.controller;

import blackjack.domain.*;
import blackjack.strategy.CardPicker;
import blackjack.util.Repeater;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private static final int BURST_SCORE = 21;
    private static final int DEALER_HIT_NUMBER = 16;
    private static final int INIT_COUNT = 2;

    private final CardPicker cardPicker;

    public BlackJackController(CardPicker cardPicker) {
        this.cardPicker = cardPicker;
    }

    public void run() {
        final Players players = Repeater.repeatIfError(this::inputPlayerNames, OutputView::printErrorMessage);
        final Dealer dealer = new Dealer();
        final Referee referee = new Referee();
        final CardPool cardPool = new CardPool();

        init(players, dealer, cardPool);
        askPlayers(players, cardPool);
        hitCardByDealer(dealer, cardPool);
        printFinal(players, dealer, referee);
    }

    private void printFinal(Players players, Dealer dealer, Referee referee) {
        List<Result> results = referee.judgeResult(dealer, players);
        OutputView.printFinalCardDeckAndScore(dealer, players);
        OutputView.printResult(referee.countDealerResult(results), dealer, players, results);
    }

    private void hitCardByDealer(Dealer dealer, CardPool cardPool) {
        OutputView.println();
        int dealerScore = dealer.calculateScore();

        while (isContinueToHit(dealerScore)) {
            dealer.hit(cardPool.draw(cardPicker));
            dealerScore = dealer.calculateScore();
            OutputView.printDealerPickMessage(dealer);
        }
    }

    private boolean isContinueToHit(int dealerScore) {
        return dealerScore <= DEALER_HIT_NUMBER;
    }

    private void askPlayers(Players players, CardPool cardPool) {
        for (Player player : players.getPlayers()) {
            askPlayer(player, cardPool);
        }
    }

    private void init(Players players, Dealer dealer, CardPool cardPool) {
        for (int i = 0; i < INIT_COUNT; i++) {
            dealer.hit(cardPool.draw(cardPicker));
        }
        players.getPlayers().forEach(player -> player.hit(cardPool.draw(cardPicker)));
        OutputView.printInitCardDeck(dealer, players);
    }

    private void askPlayer(Player player, CardPool cardPool) {
        Command command = Command.CONTINUE;
        int score = 0;

        while (isContinueToAsk(command, score)) {
            command = Repeater.repeatIfError(() -> inputCommand(player), OutputView::printErrorMessage);
            hitByCommand(player, command, cardPool);
            OutputView.printPlayerCardDeck(player);
            score = calculateScore(player);
        }
    }

    private int calculateScore(Player player) {
        int score = player.calculateScore();

        if (BURST_SCORE < score) {
            OutputView.printBurstMessage();
        }
        return score;
    }

    private void hitByCommand(Player player, Command command, CardPool cardPool) {
        if (Command.isContinue(command)) {
            player.hit(cardPool.draw(cardPicker));
        }
    }

    private boolean isContinueToAsk(Command command, int score) {
        return Command.isContinue(command) && BURST_SCORE > score;
    }

    private Players inputPlayerNames() {
        return new Players(InputView.inputPlayerNames());
    }

    private Command inputCommand(Player player) {
        return Command.toCommand(InputView.inputReply(player.getName().getValue()));
    }
}
