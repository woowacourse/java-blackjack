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
        Game game = Game.of(InputView.inputPlayerNames());

        game.setUpTwoCards();
        OutputView.printSetup(game.getDealer(), game.getPlayers());

        askPlayersDrawCard(game);
        makeDealerDrawCard(game);

        OutputView.printFinalCardInfo(game.getDealer(), game.getPlayers());
        game.comparePlayersCardsWithDealer();
        printWinOrLoseResult(game);
    }

    private void askPlayersDrawCard(Game game) {
        for (Player player : game.getPlayers()) {
            askDrawCard(player, game);
        }
    }

    private void askDrawCard(Player player, Game game) {
        while (willDrawCard(player)) {
            game.giveCard(player);
            OutputView.printCardInfo(player);
            OutputView.printMessage("");
            if (player.isBlackJack()) {
                OutputView.printMessage(BLACKJACK_MESSAGE);
                break;
            }
            if (player.isBust()) {
                OutputView.printMessage(BUST_MESSAGE);
                break;
            }
        }
    }

    private boolean willDrawCard(Player player) {
        try {
            return InputView.inputYesOrNo(player);
        } catch (IllegalArgumentException e) {
            return willDrawCard(player);
        }
    }

    private void makeDealerDrawCard(Game game) {
        int dealerDrawCount = game.playDealerTurn();
        OutputView.printDealerDrawMessage(dealerDrawCount);
        OutputView.printMessage(DEALER_NO_MORE_DRAW_MESSAGE);
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
