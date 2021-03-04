package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Casino {

    public static final String BURST_MESSAGE = "버스트이므로 더 이상 카드를 뽑지 않습니다.";
    public static final String BLACKJACK_MESSAGE = "블랙잭이므로 더 이상 카드를 뽑지 않습니다.";

    public Casino() {
    }

    public void start() {
        Game game = Game.of(InputView.inputPlayerNames());
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayers();

        game.setUpTwoCards();
        OutputView.printSetup(dealer, players);

        playerTurn(game);
        doDealerTurn(game);

        OutputView.printFinalCardInfo(dealer, players);
        game.fightPlayers();
        OutputView.printWinOrLoseResult(dealer, players);
    }

    private void playerTurn(Game game) {
        for (Player player : game.getPlayers()) {
            doPlayerTurn(player, game);
        }
    }

    private void doPlayerTurn(Player player, Game game) {
        while (InputView.inputYesOrNo(player)) {
            game.giveCard(player);
            OutputView.printCardInfo(player);
            OutputView.printMessage("");
            if (player.isBlackJack()) {
                OutputView.printMessage(BLACKJACK_MESSAGE);
                break;
            }
            if (player.isBurst()) {
                OutputView.printMessage(BURST_MESSAGE);
                break;
            }
        }
    }

    //TODO:: 0개를 뽑을 때, 메세지 추가가
    private void doDealerTurn(Game game) {
        int dealerDrawCount = game.playDealerTurn();
        OutputView.printDealerDrawMessage(dealerDrawCount);
    }
}
