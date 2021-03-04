package blackjack;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackApplication {

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        List<Player> players = getPlayers();
        BlackJackGame blackJackGame = new BlackJackGame(dealer, players);

        progressPlayersTurn(dealer, players);
        progressDealerTurn(dealer);

        GameResult result = blackJackGame.start();
        OutputView.printResult(result);
    }

    private static List<Player> getPlayers() {
        try {
            String playerNames = InputView.getPlayerName();
            return namesToPlayers(playerNames);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return getPlayers();
        }
    }

    private static List<Player> namesToPlayers(String playerNames) {
        return Arrays.stream(playerNames.split(","))
                .map(Player::from)
                .collect(Collectors.toList());
    }

    private static void progressPlayersTurn(Dealer dealer, List<Player> players) {
        for (Player player : players) {
            if (player.lowerThanThreshold() && InputView.wantsReceive(player)) {
                dealer.deal(player);
                OutputView.printCardHandStatus(player);
            }
        }
    }

    private static void progressDealerTurn(Dealer dealer) {
        while (dealer.shouldReceive()) {
            OutputView.printDealerDrewMessage();
            dealer.pickAnotherCard();
        }
    }
}
