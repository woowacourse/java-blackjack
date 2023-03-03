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
        List<Player> players = createPlayers();

        Dealer dealer = new Dealer();
        Game game = new Game(players, new Deck(), dealer);
        game.dealCards();

        outputView.printDealCards(players);
        outputView.printFirstPlayerCard(dealer);
        outputView.printPlayerCards(players);

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (game.canHit(player)) {
                if (inputView.askForAnotherCard(player.getName())) {
                    game.dealAnotherCard(i);
                }
            }
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

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            outputView.printResult(player.getName(), game.isWon(i));
        }
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
