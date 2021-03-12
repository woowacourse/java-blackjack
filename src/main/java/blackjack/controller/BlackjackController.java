package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    public static final String DEALER_NO_MORE_DRAW_MESSAGE = "딜러는 16초과라 더 이상 카드를 받지 않습니다.";

    public BlackjackController() {
    }

    public void start() {
        Game game = initGame();
        askBetting(game);
        setUpTwoCards(game);
        askPlayersDrawCard(game);
        makeDealerDrawCard(game);
        showFinalResult(game);
        printFinalRevenue(game);
    }

    private Game initGame() {
        try {
            return Game.of(InputView.inputPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return initGame();
        }
    }

    private void askBetting(Game game) {
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            askBetting(game, player);
        }
    }

    private void askBetting(Game game, Player player) {
        try {
            game.bet(player, InputView.inputBettingMoney(player));
        } catch (IllegalArgumentException e ) {
            System.out.println(e.getMessage());
            askBetting(game);
        }
    }

    private void setUpTwoCards(Game game) {
        game.startRound();
        OutputView.printSetup(game.getDealer(), game.getPlayers());
    }

    private void askPlayersDrawCard(Game game) {
        while (game.isNotEnd()) {
            Player player = game.getCurrentPlayer();
            askAndReflectReply(player, game);
            OutputView.printCardInfoWithLineSeparator(player);
        }
    }

    private void askAndReflectReply(Player player, Game game) {
        try {
            game.reflectInput(InputView.inputYesOrNo(player));
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            OutputView.printMessage(e.getMessage());
            askAndReflectReply(player, game);
        }
    }

    private void makeDealerDrawCard(Game game) {
        int dealerDrawCount = game.playDealerTurn();
        OutputView.printDealerDrawMessage(dealerDrawCount);
        OutputView.printMessage(DEALER_NO_MORE_DRAW_MESSAGE);
    }

    private void showFinalResult(Game game) {
        OutputView.printFinalCardInfo(game.getDealer(), game.getPlayers());
        game.comparePlayersCardsWithDealer();
        printWinOrLoseResult(game);
    }

    private void printWinOrLoseResult(Game game) {
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayers();
        OutputView.printWinOrLoseResult(dealer, game.getDealerResult());
        for (Player player : players) {
            OutputView.printWinOrLoseResult(player, player.getGameResult());
        }
    }

    public void printFinalRevenue(Game game) {
        game.calculateGameResult();

        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayers();
        OutputView.printFinalRevenue(dealer, game.dealerRevenue());
        for (Player player : players) {
            OutputView.printFinalRevenue(player, player.revenue());
        }
    }
}
