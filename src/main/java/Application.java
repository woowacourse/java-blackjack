import domain.Dealer;
import domain.Deck;
import domain.Game;
import domain.Player;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class Application {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {

        List<String> playerNames = inputView.readNames();
        List<Player> players = createPlayersWith(playerNames);

        Dealer dealer = new Dealer();
        Game game = new Game(players, new Deck(), dealer);
        game.dealTwoCards();

        outputView.printDealCards(players);
        outputView.printFirstPlayerCard(dealer);
        outputView.printPlayersCards(players);

        for (String playerName : playerNames) {
            selectHitAndStand(game, playerName);
        }

        if (!dealer.isOverSixteen()) {
            game.dealAnotherCard();
            outputView.noticeDealerAccept();
        } else {
            outputView.noticeDealerDecline();
        }

        outputView.printCardsAndScore(dealer);
        outputView.printCardsAndScores(players);

        System.out.println("## 최종 승패");
        outputView.printDealerResults(game.getDealerResults());

        for (String playerName : playerNames) {
            outputView.printResult(playerName, game.isWon(playerName));
        }
    }

    private static void selectHitAndStand(Game game, String playerName) {
        boolean hit = true;
        while (hit && game.canHit(playerName)) {
            hit = dealAnotherCardIfHit(game, playerName);
        }
    }

    private static boolean dealAnotherCardIfHit(Game game, String playerName) {
        if (inputView.askForAnotherCard(playerName)) {
            game.dealAnotherCard(playerName);
            outputView.printPlayerCards(playerName, game.getCards(playerName));
            return true;
        }
        return false;
    }

    private static List<Player> createPlayers() {
        List<String> playerNames = inputView.readNames();
        return createPlayersWith(playerNames);
    }

    private static List<Player> createPlayersWith(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }
}
