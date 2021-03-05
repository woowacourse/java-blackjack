package blackjack;

import blackjack.domain.game.BlackJackInitializer;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.ResultCalculator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackApplication {

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        List<Player> players = getPlayers();

        BlackJackInitializer blackJackInitializer = new BlackJackInitializer(dealer, players);
        blackJackInitializer.setBaseCardToPlayers();

        OutputView.printPlayersHandStatus(dealer, players);

        progressPlayersTurn(dealer, players);
        progressDealerTurn(dealer);

        ResultCalculator resultCalculator = new ResultCalculator(dealer, players);
        GameResult result = resultCalculator.getResult();

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
            while (!player.isBust() && InputView.wantsReceive(player.getName())) {
                dealer.deal(player);
                OutputView.printHandStatus(player);
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
