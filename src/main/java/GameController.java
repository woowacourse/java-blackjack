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
    }

    public void run() {
        for (Player player : players) {
            while (player.needMoreCard(getAnswerForNeedMoreCard(player), deck)) {
                if (player.checkBurst()) {
                    OutputView.printBurst(player);
                    continue;
                }
                OutputView.printHands(player);
            }
        }
        dealer.hit(deck);
        if (dealer.handSize() == 3) {
            OutputView.printDealerHitCard();
        }

        OutputView.printAllHands(players, dealer);

        gameResult.create(players, dealer);

        OutputView.printGameResult(gameResult);
    }

    private String getAnswerForNeedMoreCard(Player player) {
        OutputView.printNeedMoreCard(player);
        return InputView.inputYesOrNo();
    }
}
