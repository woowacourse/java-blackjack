package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {

    public static final String BUST_MESSAGE = "버스트이므로 더 이상 카드를 뽑지 않습니다.";
    public static final String BLACKJACK_MESSAGE = "블랙잭이므로 더 이상 카드를 뽑지 않습니다.";
    public static final String DEALER_NO_MORE_DRAW_MESSAGE = "딜러는 16초과라 더 이상 카드를 받지 않습니다.";

    public BlackjackController() {
    }

    public void start() {
        Game game = initGame();
        setUpTwoCards(game);
        askPlayersDrawCard(game);
        makeDealerDrawCard(game);
        showFinalResult(game);
    }

    private Game initGame() {
        try {
            return Game.of(InputView.inputPlayerNames());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return initGame();
        }
    }

    private void askPlayersDrawCard(Game game) {
        for (Player player : game.getPlayers()) {
            askDrawCard(player, game);
        }
    }

    private void setUpTwoCards(Game game) {
        game.setUpTwoCards();
        OutputView.printSetup(game.getDealer(), game.getPlayers());
    }

    private void askDrawCard(Player player, Game game) {
        while (willDrawCard(player)) {
            game.giveCard(player);
            OutputView.printCardInfoWithLineSeparator(player);
        }
    }

    private boolean willDrawCard(Player player) {
        try {
            return tryWillDrawCard(player);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return willDrawCard(player);
        }
    }

    private boolean tryWillDrawCard(Player player) {
        if (player.isBlackJack()) {
            OutputView.printMessage(BLACKJACK_MESSAGE);
            return false;
        }
        if (player.isBust()) {
            OutputView.printMessage(BUST_MESSAGE);
            return false;
        }
        return InputView.inputYesOrNo(player);
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
}
