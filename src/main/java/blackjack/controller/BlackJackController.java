package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.Result;
import blackjack.strategy.CardShuffle;
import blackjack.util.Repeater;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private final CardShuffle cardShuffle;

    public BlackJackController(CardShuffle cardShuffle) {
        this.cardShuffle = cardShuffle;
    }

    public void run() {
        final Players players = Repeater.repeatIfError(this::inputPlayerNames, OutputView::printErrorMessage);
        final Dealer dealer = new Dealer();
        final BlackJackGame blackJackGame = new BlackJackGame(cardShuffle);

        initHit(players, dealer, blackJackGame);
        askPlayers(players, blackJackGame);
        hitCardByDealer(dealer, blackJackGame);
        printFinal(players, dealer, blackJackGame);
    }

    private void initHit(Players players, Dealer dealer, BlackJackGame blackJackGame) {
        blackJackGame.initHit(players, dealer);
        OutputView.printInitCardDeck(dealer, players);
    }

    private Players inputPlayerNames() {
        return new Players(InputView.inputPlayerNames());
    }

    private void askPlayers(Players players, BlackJackGame blackJackGame) {
        for (Player player : players.getPlayers()) {
            askPlayer(player, blackJackGame);
        }
    }

    private void askPlayer(Player player, BlackJackGame blackJackGame) {
        Command command = Command.CONTINUE;
        int score = 0;

        while (isContinueToAsk(command, score, blackJackGame)) {
            command = Repeater.repeatIfError(() -> inputCommand(player), OutputView::printErrorMessage);
            hitByCommand(player, command, blackJackGame);
            OutputView.printPlayerCardDeck(player);
            score = blackJackGame.calculateScore(player);
            checkBurst(blackJackGame, score);
        }
    }

    private boolean isContinueToAsk(Command command, int score, BlackJackGame blackJackGame) {
        return Command.isContinue(command) && blackJackGame.isValidScore(score);
    }

    private Command inputCommand(Player player) {
        return Command.toCommand(InputView.inputReply(player.getName().getValue()));
    }

    private void hitByCommand(Player player, Command command, BlackJackGame blackJackGame) {
        if (Command.isContinue(command)) {
            blackJackGame.hit(player);
        }
    }

    private void checkBurst(BlackJackGame blackJackGame, int score) {
        if (blackJackGame.isBurst(score)) {
            OutputView.printBurstMessage();
        }
    }

    private void hitCardByDealer(Dealer dealer, BlackJackGame blackJackGame) {
        OutputView.println();
        int dealerScore = blackJackGame.calculateScore(dealer);

        while (blackJackGame.isContinueToHit(dealerScore)) {
            blackJackGame.hit(dealer);
            dealerScore = blackJackGame.calculateScore(dealer);
            OutputView.printDealerPickMessage(dealer);
        }
    }

    private void printFinal(Players players, Dealer dealer, BlackJackGame blackJackGame) {
        List<Result> results = blackJackGame.getPlayersResult(dealer, players);

        OutputView.printFinalCardDeckAndScore(dealer, players);
        OutputView.printResult(blackJackGame.getDealerResult(results), dealer, players, results);
    }
}
