package blackjack.controller;

import blackjack.domain.cardpack.MasterShuffleStrategy;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Money;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = getPlayers();
        Dealer dealer = new Dealer();
        BlackjackGame blackjackGame = new BlackjackGame(new MasterShuffleStrategy());

        playersBetting(players, blackjackGame);
        startGame(players, dealer, blackjackGame);
        processGame(players, dealer, blackjackGame);
        endGame(players, dealer, blackjackGame);
    }

    private void playersBetting(final Players players, final BlackjackGame blackjackGame) {
        players.getPlayers().forEach(player ->
                blackjackGame.addBetting(player, getPlayerBettingMoney(player))
        );
    }

    private Money getPlayerBettingMoney(final Player player) {
        return repeatInput(() -> Money.betting(inputView.readBettingMoney(player)));
    }

    private void startGame(final Players players, final Dealer dealer, final BlackjackGame blackjackGame) {
        blackjackGame.initDraw(dealer);
        players.getPlayers().forEach(blackjackGame::initDraw);
        outputView.printCardInitDrawResultMessage(players, dealer);
        outputView.printUserCards(dealer.getName(), dealer.getDealerOneCards());
        players.getPlayers().forEach((player -> outputView.printUserCards(player.getName(), player.getCards())));
    }

    private void processGame(final Players players, final Dealer dealer, final BlackjackGame blackjackGame) {
        players.getPlayers().forEach((player -> processPlayerDraw(blackjackGame, player)));
        processDealerDraw(dealer, blackjackGame);
    }

    private void processPlayerDraw(final BlackjackGame blackjackGame, final Player player) {
        while (!player.isBust() && readCommand(player)) {
            blackjackGame.draw(player);
            printDrawResult(player);
        }
    }

    private boolean readCommand(final User user) {
        return repeatInput(() -> inputView.readCommand(user));
    }

    private void printDrawResult(final Player player) {
        outputView.printUserCards(player.getName(), player.getCards());
        if (player.isBust()) {
            OutputView.printBust();
        }
    }

    private void processDealerDraw(final Dealer dealer, final BlackjackGame blackjackGame) {
        while (!dealer.isBust() && !dealer.isSatisfiedMinScore()) {
            blackjackGame.draw(dealer);
            outputView.printDealerDraw();
        }
    }


    private void endGame(final Players players, final Dealer dealer, final BlackjackGame blackjackGame) {
        outputView.printCardResult(dealer);
        players.getPlayers().forEach((outputView::printCardResult));
        printProfitResult(blackjackGame.getUserProfit(dealer.getResult(players)));
    }

    private void printProfitResult(final Map<User, Money> profitResult) {
        outputView.introduceProfitResult();
        profitResult.keySet()
                .forEach(user -> outputView.printUserProfit(user.getName(), profitResult.get(user).getValue())
                );
    }

    private Players getPlayers() {
        return repeatInput(() -> {
            List<String> playerNames = inputView.readParticipantName();
            return new Players(playerNames.stream()
                    .map(Player::new)
                    .collect(Collectors.toList()));
        });
    }

    private <T> T repeatInput(Supplier<T> input) {
        try {
            return input.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return repeatInput(input);
        }
    }
}
