import domains.card.Deck;
import domains.result.GameResult;
import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;
import domains.user.name.PlayerName;
import domains.user.name.PlayerNames;
import view.InputView;
import view.OutputView;

public class GameController {
    private Deck deck;
    private Players players;
    private Dealer dealer;

    public GameController() {
        deck = new Deck();
        players = new Players();

        OutputView.printInputPlayerNames();
        PlayerNames playerNames = new PlayerNames(InputView.inputPlayerNames());

        bet(playerNames);

        dealer = new Dealer(deck);

        OutputView.printInitialHands(players, dealer);

        run();
    }

    private void bet(PlayerNames playerNames) {
        for (PlayerName name : playerNames) {
            OutputView.printInputBettingMoney(name);
            players.add(new Player(name, InputView.inputBettingMoney(), deck));
        }
    }

    private void run() {
        hitOrStay();

        if (dealer.isHit()) {
            OutputView.printDealerHitCard();
        }
        OutputView.printAllHands(players, dealer);

        GameResult gameResult = new GameResult(players, dealer);

        OutputView.printGameResult(gameResult);
    }

    private void hitOrStay() {
        for (Player player : players) {
            needMoreCard(player);
        }

        dealer.hitOrStay(deck);
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
