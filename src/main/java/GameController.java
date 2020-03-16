import domains.card.Deck;
import domains.result.GameResult;
import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;
import view.InputView;
import view.OutputView;

public class GameController {
    private Deck deck;
    private Players players;
    private Dealer dealer;
    private GameResult gameResult;

    public GameController() {
        deck = new Deck();
        gameResult = new GameResult();
        OutputView.printInputPlayerNames();
        players = new Players(InputView.inputPlayerNames(), deck);
        dealer = new Dealer(deck);
        OutputView.printInitialHands(players, dealer);
        run();
    }

    private void run() {
        hitOrStay();
        dealer.hit(deck);
        if (dealer.handSize() == 3) {
            OutputView.printDealerHitCard();
        }
        OutputView.printAllHands(players, dealer);

        gameResult.create(players, dealer);
        OutputView.printGameResult(gameResult);
    }

    private void hitOrStay() {
        for (Player player : players) {
            needMoreCard(player);
        }
    }

    private void needMoreCard(Player player) {
        while (player.needMoreCard(getAnswerForNeedMoreCard(player), deck)) {
            if (player.isBurst()) {
                OutputView.printBurst(player);
                break;
            }
            OutputView.printHands(player);
        }
    }

    private String getAnswerForNeedMoreCard(Player player) {
        OutputView.printNeedMoreCard(player);
        return InputView.inputYesOrNo();
    }
}
