package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Casino {

    public static final String BUST_MESSAGE = "버스트이므로 더 이상 카드를 뽑지 않습니다.";
    public static final String BLACKJACK_MESSAGE = "블랙잭이므로 더 이상 카드를 뽑지 않습니다.";
    public static final String DEALER_NO_MORE_DRAW_MESSAGE = "딜러는 16초과라 더 이상 카드를 받지 않습니다.";

    public Casino() {
    }

    public void start() {
        Game game = Game.of(InputView.inputPlayerNames());
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayers();

        game.setUpTwoCards();
        OutputView.printSetup(dealer, players);

        askPlayersDrawCard(game);
        makeDealerDrawCard(game);

        OutputView.printFinalCardInfo(dealer, players);
        game.comparePlayersCardsWithDealer();
        printWinOrLoseResult(game);
    }

    private void askPlayersDrawCard(Game game) {
        for (Player player : game.getPlayers()) {
            askDrawCard(player, game);
        }
    }

    private void askDrawCard(Player player, Game game) {
        while (InputView.inputYesOrNo(player)) {
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
