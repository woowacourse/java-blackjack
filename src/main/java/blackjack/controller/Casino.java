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

    private final Game game;

    public Casino() {
        game = Game.of(InputView.inputPlayerNames());
    }

    public void blackJack() {
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayers();

        setUpTwoCards(dealer, players);
        playerTurn();
        dealerTurn();
        closingStage(dealer, players);
    }

    private void setUpTwoCards(Dealer dealer, List<Player> players) {
        game.setUpTwoCards();
        OutputView.printSetup(dealer, players);
    }

    private void playerTurn() {
        for (Player player : game.getPlayers()) {
            doPlayerTurn(player);
        }
    }

    private void doPlayerTurn(Player player) {
        while (InputView.inputYesOrNo(player)) {
            game.giveCard(player);
            OutputView.printCardInfo(player);
            OutputView.printMessage("");
            if (player.isBlackJack()) {
                OutputView.printMessage(BLACKJACK_MESSAGE);
                OutputView.lineFeed();
                break;
            }
            if (player.isBurst()) {
                OutputView.printMessage(BURST_MESSAGE);
                OutputView.lineFeed();
                break;
            }
        }
    }

    private void dealerTurn() {
        int dealerDrawCount = game.playDealerTurn();
        OutputView.printDealerDraw(dealerDrawCount);
        OutputView.printDealerNoMoreDraw();
    }

    private void closingStage(Dealer dealer, List<Player> players) {
        OutputView.printParticipantFinalCardInfo(dealer, players);
        game.fightPlayers();
        OutputView.printWinOrLoseResult(dealer, players);
    }
}
