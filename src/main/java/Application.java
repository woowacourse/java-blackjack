import domain.Dealer;
import domain.Deck;
import domain.Game;
import domain.Player;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        List<String> playerNames = inputView.readNames();

        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());

        Dealer dealer = new Dealer();
        Game game = new Game(players, new Deck(), dealer);
        game.dealCards();

        outputView.printDealCards(players);
        outputView.printFirstPlayerCard(dealer);
        outputView.printPlayerCards(players);

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (player.getScore() < 21) {
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
}
